package com.echo.service.orderservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.echo.dao.orderdao.OrderDAOImpl;
import com.echo.domain.po.Hotel;
import com.echo.domain.po.Order;
import com.echo.domain.type.OrderStatusType;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	public OrderDAOImpl orderDAOImpl;

	@Override
	public boolean generateOrder(Order order) {
		return orderDAOImpl.add(order);
	}

	@Override
	public boolean updateOrderStatus(Order order, byte orderTypeID) {
		order.setOrderType(orderTypeID);
		return orderDAOImpl.update(order);
	}

	@Override
	public List<Order> getCustomerOrders(int customerID) {
		return orderDAOImpl.getOrdersByCID(customerID);
	}

	@Override
	public List<Order> getCustomerOrdersByHotel(int customerID, int hotelID) {
		return orderDAOImpl.getOrdersByCIDAndHID(customerID, hotelID);
	}

	@Override
	public List<Order> getCustomerOrdersByType(int customerID, byte orderType) {
		return orderDAOImpl.getOrdersByCIDAndOrderType(customerID, orderType);
	}

	@Override
	public List<Order> getHotelOrders(int hotelID) {
		return orderDAOImpl.getOrdersByHID(hotelID);
	}

	@Override
	public List<Order> getHotelOrdersByType(int hotelID, byte orderType) {
		return orderDAOImpl.getOrdersByHIDAndOrderType(hotelID, orderType);
	}

	@Override
	public List<Order> getUnexecutedOrderToday() {
		return orderDAOImpl.getOrdersByTypeToday(OrderStatusType.UNEXECUTED);
	}

	@Override
	public Map<String, Integer> getOrdersSize(int hotelID) {
		Map<String,Integer> map = new HashMap<>();
		int unexecuted  = orderDAOImpl.getOrdersSizeByType(hotelID,OrderStatusType.UNEXECUTED);
		int executed  = orderDAOImpl.getOrdersSizeByType(hotelID,OrderStatusType.EXECUTED)
						+orderDAOImpl.getOrdersSizeByType(hotelID,OrderStatusType.EVALUATED);
		int cancelled  = orderDAOImpl.getOrdersSizeByType(hotelID,OrderStatusType.CANCELLED);
		int abnormal  = orderDAOImpl.getOrdersSizeByType(hotelID,OrderStatusType.ABNORMAL);
		map.put("unexecuted",unexecuted );
		map.put("executed", executed);
		map.put("cancelled", cancelled);
		map.put("abnormal", abnormal);
		return map;
	}

	@Override
	public List<Order> searchAbnormalOrder(String phone) {
		return orderDAOImpl.getOrdersByCPhone(phone, OrderStatusType.ABNORMAL);
	}

	@Override
	public boolean handleAbnoremalOrder(int orderID) {
		Order order = orderDAOImpl.get(orderID);
		order.setOrderType(OrderStatusType.CANCELLED);
		return orderDAOImpl.update(order);
	}


	@Override
	public List<Order> getOrdersByPhone(String phone, int hotelID) {
		return 	orderDAOImpl.getOrdersByHIDAndCPhone(hotelID, phone);
	}

	@Override
	public Order getOrderByID(int orderID) {
		return orderDAOImpl.get(orderID);
	}
	
	/**
	 * 对用户的预订记录进行统计
	 */
	public Map<Hotel,Integer> getOrderTimesByHotel(int customerID){
		List<Order> orders = getCustomerOrders(customerID);
		Map<Hotel,Integer> map = new HashMap<>();
		for(Order order : orders){
			if(map.containsKey(order.getHotel())){
				int times = map.get(order.getHotel());
				map.put(order.getHotel(), times+1);
			}else{
				map.put(order.getHotel(), 1);
			}
		}
		return map;
	}

}
