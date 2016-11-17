package com.echo.service.test;

import java.util.List;
import java.util.Map;

import com.echo.domain.po.Order;
import com.echo.service.orderservice.OrderService;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class OrderService_stub implements OrderService {
    public boolean generateOrder(Order order) {
        System.out.println("generateOrder successfully");
        return false;
    }

    public boolean updateOrderStatus(Order order, byte orderTypeID) {
        System.out.println("updateOrderStatus successfully");
        return false;
    }

    public List<Order> getCustomerOrders(int customerID) {
        System.out.println("getCustomerOrders successfully");
        return null;
    }

    public List<Order> getCustomerOrdersByHotel(int customerID, int hotelID) {
        System.out.println("getCustomerOrdersByHotel successfully");
        return null;
    }

    public List<Order> getCustomerOrdersByType(int customerID, byte orderType) {
        System.out.println("getCustomerOrdersByType successfully");
        return null;
    }

    public List<Order> getHotelOrders(int hotelID) {
        System.out.println("getHotelOrders successfully");
        return null;
    }

    public List<Order> getHotelOrdersByType(int customerID, byte orderType) {
        System.out.println("getHotelOrdersByType successfully");
        return null;
    }

    public List<Order> searchAbnormalOrder(String phone) {
        System.out.println("searchAbnormalOrder successfully");
        return null;
    }


    public List<Order> getUnexecutedOrderToday() {
        System.out.println("getUnexecutedOrderToday successfully");
        return null;
    }

	@Override
	public Order getOrderByID(int orderID) {
		  System.out.println("getOrderByID successfully");
		return null;
	}

	@Override
	public boolean handleAbnoremalOrder(int orderID) {
		System.out.println("getOrderByID successfully");
		return false;
	}

	@Override
	public List<Order> getOrdersByPhone(String phone, int hotelID) {
		System.out.println("getOrderByID successfully");
		return null;
	}

	@Override
	public Map<String, Integer> getOrdersSize(int hotelID) {
		System.out.println("getOrderByID successfully");
		return null;
	}
}
