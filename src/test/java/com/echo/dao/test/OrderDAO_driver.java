package com.echo.dao.test;

import com.echo.dao.orderdao.OrderDAO;
import com.echo.domain.po.Order;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class OrderDAO_driver {
    public void drive(OrderDAO orderDAO) {
       orderDAO.get(0);
        orderDAO.getOrdersByCID(0);
        orderDAO.getOrdersByCIDAndHID(0,0);
        orderDAO.getOrdersByCIDAndOrderType(0,new Byte("a"));
        orderDAO.getOrdersByCPhone("100",new Byte("a"));
        orderDAO.getOrdersByHID(0);
        orderDAO.getOrdersByHIDAndCPhone(0,"100");
        orderDAO.getOrdersByTypeToday(new Byte("a"));
        orderDAO.getOrdersByHIDAndOrderType(0,new Byte("a"));
        orderDAO.add(new Order());
        orderDAO.update(new Order());
    }

    public void main(String[] args){
        (new OrderDAO_driver()).drive(new OrderDAO_stub()) ;
    }
}
