package com.echo.test.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.echo.dao.orderdao.OrderDAOImpl;
import com.echo.domain.po.Hotel;
import com.echo.domain.po.Order;
import com.echo.domain.po.RoomType;
import com.echo.domain.type.OrderStatusType;
/**
 * Order DAO Test
 * @author lenovo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "WebContent")
@ContextHierarchy({
        @ContextConfiguration(  locations = "classpath:applicationContext.xml"),
})
public class OrderTest  extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	public OrderDAOImpl orderDAOImpl;
	
	@Test
	public void testAddOrder(){
		Hotel hotel = new Hotel();
		hotel.setHotelID(1);
		RoomType rt = new RoomType();
		rt.setTypeID(2);
		Order order = new Order(OrderStatusType.UNEXECUTED, hotel, 3, "reservedName", "reservedPhone", new Date(), new Date(), new Date(), new Date(), rt, 3, 3, (byte)1);
		assertTrue(orderDAOImpl.add(order));
	}
	
	@Test
	public void testGetOrder(){
		assertNotNull(orderDAOImpl.get(10023500));
	}
	
	@Test
	public void testUpdateOrder(){
		Order order = orderDAOImpl.get(10023500);
		order.setBookingNum(4);
		orderDAOImpl.update(order);
	}
	
	@Test
	public void testGetOrdersByCID(){
		assertThat(orderDAOImpl.getOrdersByCID(3).size(),greaterThan(0));
	}
	
	@Test
	public void testGetOrdersByCIDAndHID(){
		assertThat(orderDAOImpl.getOrdersByCIDAndHID(3, 1).size(),greaterThan(0));
	}
	
	@Test
	public void testGetOrdersByCIDAndOrderType(){
		assertNotNull(orderDAOImpl.getOrdersByCIDAndOrderType(3, OrderStatusType.EXECUTED));
	}
	
	@Test
	public void testGetOrdersByHID(){
		assertNotNull(orderDAOImpl.getOrdersByHID(1));
	}
	
	@Test
	public void testGetOrdersByHIDAndOrderType(){
		assertNotNull(orderDAOImpl.getOrdersByHIDAndOrderType(1,OrderStatusType.EXECUTED));
	}
	
	@Test
	public void testGetOrdersByTypeToday(){
		assertThat(orderDAOImpl.getOrdersSizeByType(1, OrderStatusType.UNEXECUTED),greaterThanOrEqualTo(0));
	}
	
	@Test
	public void testGetOrdersSizeByType(){
		assertThat(orderDAOImpl.getOrdersSizeByType(1, OrderStatusType.UNEXECUTED),greaterThan(0));
	}
	
}
