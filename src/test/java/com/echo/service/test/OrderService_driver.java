package com.echo.service.test;

import com.echo.domain.po.Order;
import com.echo.service.orderservice.OrderService;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class OrderService_driver {
    public void drive(OrderService ordeService){
        ordeService.generateOrder(new Order());
        ordeService.getCustomerOrders(0);
        ordeService.getCustomerOrdersByHotel(0,0);
        ordeService.getCustomerOrdersByType(0,new Byte("a"));
        ordeService.getHotelOrders(0);
        ordeService.getHotelOrdersByType(0,new Byte("a"));
        ordeService.getUnexecutedOrderToday();
        ordeService.handleAbnoremalOrder(0);
        ordeService.searchAbnormalOrder("100");
        ordeService.updateOrderStatus(new Order(),new Byte("a"));
    }

    public static void main(String[] args) {
        (new OrderService_driver()).drive(new OrderService_stub());
    }
}
