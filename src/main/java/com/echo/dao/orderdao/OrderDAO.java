package com.echo.dao.orderdao;

import java.util.List;
import com.echo.domain.po.Order;

public interface OrderDAO {
	
	/**
	 * 保存订单信息
	 * @param order
	 * @return
	 */
	public boolean add(Order order);
	
	/**
	 * 修改订单信息
	 * @param order
	 * @return
	 */
	public boolean update(Order order);
	
	/**
	 * 按照订单ID获得整个订单
	 * @param orderID
	 * @return
	 */
	public Order get(int orderID);
	
	/**
	 * 获取用户的所有订单
	 * @param customerID
	 * @return
	 */
	public List<Order> getOrdersByCID(int customerID);
	
	/**
	 * 获取客户在某酒店的所有订单
	 * @param customerID 顾客ID
	 * @param hotelID  酒店ID
	 * @return
	 */
	public List<Order> getOrdersByCIDAndHID(int customerID, int hotelID);
	
	/**
	 * 获取客户的不同状态的订单
	 * @param customerID 客户ID
	 * @param typeID  订单状态类型
	 * @return
	 */
	public List<Order> getOrdersByCIDAndOrderType(int customerID, byte orderTypeID);
	
	/**
	 * 获取酒店的所有订单
	 * @param hotelID 酒店编号
	 * @return
	 */
	public List<Order> getOrdersByHID(int hotelID);
	
	/**
	 * 获取酒店不同类型的订单
	 * @param hotelID 酒店编号
	 * @param orderType 订单状态
	 * @return
	 */
	public List<Order> getOrdersByHIDAndOrderType(int hotelID, byte orderTypeID);
	
	/**
	 * 按照用户手机号，获得在某个酒店的订单列表（可用于客户入住）
	 * @param hotelID
	 * @param customerName
	 * @return
	 */
	public List<Order> getOrdersByHIDAndCPhone(int hotelID,String phone);
	
	/**
	 * 按照用户手机号，获取其（不同状态的）订单
	 * @param phone
	 * @return
	 */
	public List<Order> getOrdersByCPhone(String phone, byte orderType);
	
	/**
	 * 按照订单状态分类来获得整个平台当日的订单列表
	 * @param orderTypeID 订单状态ID
	 * @return
	 */
	public List<Order> getOrdersByTypeToday(byte orderTypeID);
	
	/**
	 * 获取酒店某类订单的数量
	 * @param hotelID
	 * @param orderType
	 * @return
	 */
	public int getOrdersSizeByType(int hotelID,byte orderType);
	
	/**
	 * 获取当日的异常订单（WebMarketer）
	 * @return
	 */
	public List<Order> getAbnormalOrdersToday();

}
