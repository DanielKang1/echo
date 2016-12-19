package com.echo.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.echo.dao.orderdao.OrderDAOImpl;
import com.echo.domain.po.HotelPromotionItem;
import com.echo.domain.po.Order;
import com.echo.domain.po.WebMarketer;
import com.echo.domain.type.OrderStatusType;
import com.echo.service.hotelpromotionservice.HotelPromotionServiceImpl;
import com.echo.service.hotelstaffservice.HotelStaffServiceImpl;
import com.echo.service.webmarketerservice.WebMarketerServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "WebContent")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath:applicationContext.xml"),
        @ContextConfiguration(name = "child", locations = "classpath:springmvc.xml")
})
public class PromotionTest {
	@Autowired
	private HotelPromotionServiceImpl impl;
	
	@Test
	public void testInsert(){
		HotelPromotionItem item = new HotelPromotionItem(3, 1, false, 10, 1, false, 1, false);
		impl.addHotelPromotionItem(item);
	}
	
	@Autowired
	private WebMarketerServiceImpl impl2;
	
	@Test
	public void test(){
		WebMarketer marketer = new WebMarketer();
		marketer.setMarketerName("marketer1");
		marketer.setPwd("marketer1");
		impl2.addMarketer(marketer);
	}
	
	@Autowired
	private HotelStaffServiceImpl impl4;
	
	@Test
	public void test5(){
		impl4.addStaff(1, "11111111", "fch", "13412341234");
	}
	
	@Autowired
	private OrderDAOImpl impl3;
	@Test
	public void test2(){
		List<Order> orders = impl3.getOrdersByTypeToday(OrderStatusType.ABNORMAL);
		System.out.println(orders.size());
	}

}
