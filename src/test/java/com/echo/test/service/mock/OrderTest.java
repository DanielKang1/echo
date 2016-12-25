package com.echo.test.service.mock;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.echo.dao.orderdao.OrderDAOImpl;
import com.echo.domain.po.Hotel;
import com.echo.domain.po.Order;
import com.echo.domain.type.OrderStatusType;
import com.echo.service.orderservice.OrderServiceImpl;

public class OrderTest {
	
	@InjectMocks
	private OrderServiceImpl mockOrderServiceImpl;
	
	@Mock
	private OrderDAOImpl mockOrderDAOImpl;
	
	@Before
	public void initMock(){
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * 测试Customer生成订单，查看订单列表，取消订单
	 */
	@Test
	public void testOrder1(){
		 Order order = new Order();
		 order.setOrderID(2147000007);
		 order.setCustomerID(10100);
		 order.setOrderType(OrderStatusType.UNEXECUTED);
		 List<Order> orders = new ArrayList<>();
		 orders.add(order);
		 
		 when(mockOrderDAOImpl.add(any(Order.class))).thenReturn(true);
		 when(mockOrderDAOImpl.update(any(Order.class))).thenReturn(true);
		 when(mockOrderDAOImpl.getOrdersByCID(any(int.class))).thenReturn(orders);
		 
		 assertTrue(mockOrderServiceImpl.generateOrder(order));
		 List<Order> result = mockOrderServiceImpl.getCustomerOrders(10100);
		 assertNotNull(result);
		 assertThat(result.size(), is(1));
		 assertTrue(mockOrderServiceImpl.updateOrderStatus(result.get(0), OrderStatusType.CANCELLED));
		 assertThat(result.get(0).getOrderType(), is(OrderStatusType.CANCELLED));
		 
		 verify(mockOrderDAOImpl,times(1)).add(any(Order.class));
		 verify(mockOrderDAOImpl,times(1)).update(any(Order.class));
		 verify(mockOrderDAOImpl,times(1)).getOrdersByCID(anyInt());
	}
	
	/**
	 * 测试Customer查看不同类型订单（按类型、按酒店）
	 */
	@Test
	public void testOrder2(){
		 Hotel hotel = new Hotel();
		 hotel.setHotelID(1);
		 Order order = new Order();
		 order.setOrderID(2147000007);
		 order.setCustomerID(10100);
		 order.setHotel(hotel);
		 List<Order> orders = new ArrayList<>();
		 orders.add(order);
		 
		 when(mockOrderDAOImpl.getOrdersByCIDAndHID(anyInt(),anyInt())).thenReturn(orders);
		 when(mockOrderDAOImpl.getOrdersByCIDAndOrderType(anyInt(),any(byte.class))).thenReturn(orders);
		 assertNotNull(mockOrderServiceImpl.getCustomerOrdersByType(10100, OrderStatusType.CANCELLED));
		 assertNotNull(mockOrderServiceImpl.getCustomerOrdersByHotel(10100, 1));
		 
		 verify(mockOrderDAOImpl,times(1)).getOrdersByCIDAndHID(anyInt(),anyInt());
		 verify(mockOrderDAOImpl,times(1)).getOrdersByCIDAndOrderType(anyInt(),any(byte.class));
	}
	
	/**
	 * 测试HotelStaff查看汇总与不同类型订单 
	 */
	@Test
	public void testOrder3(){
		 List<Order> orders = new ArrayList<>();
		 when(mockOrderDAOImpl.getOrdersByHID(anyInt())).thenReturn(orders);
		 when(mockOrderDAOImpl.getOrdersByHIDAndOrderType(anyInt(),any(byte.class))).thenReturn(orders);
		 when(mockOrderDAOImpl.getOrdersByTypeToday(OrderStatusType.UNEXECUTED)).thenReturn(orders);
		 assertNotNull(mockOrderServiceImpl.getHotelOrders(10100));
		 assertNotNull(mockOrderServiceImpl.getHotelOrdersByType(10100,OrderStatusType.UNEXECUTED));
		 assertNotNull(mockOrderServiceImpl.getUnexecutedOrderToday());
		 
		 verify(mockOrderDAOImpl,times(1)).getOrdersByHID(anyInt());
		 verify(mockOrderDAOImpl,times(1)).getOrdersByHIDAndOrderType(anyInt(),any(byte.class));
		 verify(mockOrderDAOImpl,times(1)).getOrdersByTypeToday(any(byte.class));
	}
	
	/**
	 * 测试WebMarketer查询异常订单、撤销异常订单
	 */
	@Test
	public void testOrder4(){
		 Order order = new Order();
		 List<Order> orders = new ArrayList<>();
		 when(mockOrderDAOImpl.getOrdersByCPhone(anyString(),any(byte.class))).thenReturn(orders);
		 when(mockOrderDAOImpl.get(anyInt())).thenReturn(order);
		 when(mockOrderDAOImpl.update(order)).thenReturn(true);
		 
		 assertNotNull(mockOrderServiceImpl.searchAbnormalOrder("13212341234"));
		 assertTrue(mockOrderServiceImpl.handleAbnoremalOrder(12123100));
		 
		 verify(mockOrderDAOImpl,times(1)).getOrdersByCPhone(anyString(),any(byte.class));
		 verify(mockOrderDAOImpl,times(1)).get(anyInt());
		 verify(mockOrderDAOImpl,times(1)).update(any(Order.class));
	}
	

}
