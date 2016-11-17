package com.echo.service.orderservice;

import java.util.List;
import java.util.Map;

import com.echo.domain.po.Order;

public interface OrderService {

	/**
	 * 按照ID获得Order
	 * @param orderID
	 * @return
	 */
	public Order getOrderByID(int orderID);
	
	/**
	 * 用户生成订单
	 * @param order
	 * @return
	 */
	public boolean generateOrder(Order order);
	
	/**
	 * 修改订单的状态（正常订单、异常订单、已撤销订单，异常订单）
	 * @param order
	 * @param orderTypeID
	 * @return
	 */
	public boolean updateOrderStatus(Order order, byte orderTypeID);
	
	/**
	 * 用户获取自己的所有订单
	 * @param customerID
	 * @return
	 */
	public List<Order> getCustomerOrders(int customerID);
	
	/**
	 * 获取用户在某个酒店的订单
	 * @param customerID
	 * @param hotelID
	 * @return
	 */
	public List<Order> getCustomerOrdersByHotel(int customerID, int hotelID);
	
	/**
	 * 获取不同类型的某个用户的订单
	 * @param customerID
	 * @param orderType
	 * @return
	 */
	public List<Order> getCustomerOrdersByType(int customerID, byte orderType);
	
	/**
	 * 获取酒店所有订单
	 * @param hotelID
	 * @return
	 */
	public List<Order> getHotelOrders(int hotelID);
	
	/**
	 * 获取酒店所有（某种状态的）订单
	 * @param customerID
	 * @param orderType
	 * @return
	 */
	public List<Order> getHotelOrdersByType(int hotelID, byte orderType);
	
	/**
	 * 按照用户手机号，获取用户所有异常订单
	 * @param phone
	 * @return
	 */
	public List<Order> searchAbnormalOrder(String phone);
	
	/**
	 * 处理申诉的异常订单
	 * @param order
	 * @return
	 */
	public boolean handleAbnoremalOrder(int orderID);
	
	/**
	 * 获取平台当日未执行的订单集合
	 * @return
	 */
	public List<Order> getUnexecutedOrderToday();
	
	/**
	 * 按照用户手机号获得要进行处理的订单
	 * @return
	 */
	public List<Order> getOrdersByPhone(String phone,int hotelID);
	
	/**
	 * 获得四类订单的数量
	 */
	public Map<String,Integer> getOrdersSize(int hotelID);
	
	
	
}
