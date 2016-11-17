package com.echo.dao.test;

import java.util.List;

import com.echo.dao.orderdao.OrderDAO;
import com.echo.domain.po.Order;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class OrderDAO_stub implements OrderDAO {
    public boolean add(Order order) {
        System.out.println("save successfully");
        return false;
    }

    public boolean update(Order order) {
        System.out.println("update successfully");
        return false;
    }

    public Order get(int orderID) {
        System.out.println("show order");
        return null;
    }

    public List<Order> getOrdersByCID(int customerID) {
        System.out.println("show orders");
        return null;
    }

    public List<Order> getOrdersByCIDAndHID(int customerID, int hotelID) {
        System.out.println("show orders");
        return null;
    }

    public List<Order> getOrdersByCIDAndOrderType(int customerID, byte orderTypeID) {
        System.out.println("show orders");
        return null;
    }

    public List<Order> getOrdersByHID(int hotelID) {
        System.out.println("show orders");
        return null;
    }

    public List<Order> getOrdersByHIDAndOrderType(int hotelID, byte orderTypeID) {
        System.out.println("show orders");
        return null;
    }

    public List<Order> getOrdersByHIDAndCPhone(int hotelID, String phone) {
        System.out.println("show orders");
        return null;
    }

    public List<Order> getOrdersByCPhone(String phone, byte orderType) {
        System.out.println("show orders");
        return null;
    }

    public List<Order> getOrdersByTypeToday(byte orderTypeID) {
        System.out.println("show orders");
        return null;
    }

	@Override
	public int getOrdersSizeByType(int hotelID, byte orderType) {
		System.out.println("getOrdersSizeByType ");
		return 0;
	}
}
