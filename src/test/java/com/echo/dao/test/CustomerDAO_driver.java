package com.echo.dao.test;

import com.echo.dao.customerdao.CustomerDAO;
import com.echo.domain.po.Customer;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class CustomerDAO_driver {
    public void drive(CustomerDAO customerDAO) {
        customerDAO.get(0);
        customerDAO.get("abc","123");
        customerDAO.add(new Customer());
        customerDAO.update(new Customer());

    }

    public void main(String[] args){
        (new CustomerDAO_driver()).drive(new CustomerDAO_stub()) ;
    }
}
