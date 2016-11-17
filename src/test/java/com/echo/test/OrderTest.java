package com.echo.test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.echo.dao.orderdao.OrderDAOImpl;
import com.echo.domain.po.Order;
import com.echo.domain.type.OrderStatusType;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "WebContent")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath:applicationContext.xml"),
        @ContextConfiguration(name = "child", locations = "classpath:springmvc.xml")
})
public class OrderTest {
	
	@Autowired
	public OrderDAOImpl orderDAOImpl;
	
//	@Test
//	public void testAddOrder(){
//		Order order = new Order();
//		order.setHotelID(1);
//		order.setCustomerID(1);
//		order.setOrderType((byte)1);
//		order.setHotelName("南京金陵饭店");
//		order.setRoomType(1);
//		orderDAOImpl.add(order);
//	}
//	
//	@Test
//	public void testUpdateOrder(){
//		Order order = new Order();
//		order.setHotelID(1);
//		order.setCustomerID(1);
//		order.setOrderType((byte)1);
//		order.setHotelName("南京金陵饭店");
//		order.setRoomType(1);
//		order.setBookingNum(2);
//		order.setHasChild((byte)1);
//		order.setOrderID(1);
//		orderDAOImpl.update(order);
//	}
	
	@Test
	public void testGetOrder(){
//		System.out.println(orderDAOImpl.get(1));
		assertNotNull(orderDAOImpl.get(1));
	}
	
	@Test
	public void testGetOrdersByCID(){
//		System.out.println(orderDAOImpl.getOrdersByCID(1));
		assertNotNull(orderDAOImpl.getOrdersByCID(1));
	}
	
	@Test
	public void testGetOrdersByCIDAndHID(){
		assertNotNull(orderDAOImpl.getOrdersByCIDAndHID(1, 1));
	}
	
	@Test
	public void testGetOrdersByCIDAndOrderType(){
		assertNotNull(orderDAOImpl.getOrdersByCIDAndOrderType(1, (byte)1));
	}
	
	@Test
	public void testGetOrdersByHID(){
		assertNotNull(orderDAOImpl.getOrdersByHID(1));
	}
	
	@Test
	public void testGetOrdersByHIDAndOrderType(){
		System.out.println(orderDAOImpl.getOrdersByHIDAndOrderType(1, (byte)0));
		assertNotNull(orderDAOImpl.getOrdersByHIDAndOrderType(1, (byte)0));
	}
	
	@Test
	public void testGetOrdersByTypeToday(){
		assertNotNull(orderDAOImpl.getOrdersByTypeToday((byte)1));
	}
	
	@Test
	public void testGetOrdersSizeByType(){
		System.out.println(orderDAOImpl.getOrdersSizeByType(1, OrderStatusType.UNEXECUTED));
	}
	

}
