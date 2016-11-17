package com.echo.dao.test;
import java.util.List;

import com.echo.dao.customerdao.CustomerDAO;
import com.echo.domain.po.Customer;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class CustomerDAO_stub implements CustomerDAO{
    public Customer get(int customerID) {
        System.out.println("show customer");
        return null;
    }

    public Customer get(String filedValue, String pwd) {
        System.out.println("show customer");
        return null;
    }

    public void save(Customer customer) {
        System.out.println("save successfully");

    }

    public boolean check(String customerID, String pwd) {
        System.out.println("check successfully");
        return false;
    }


    public boolean fieldHasSameValue(String fieldName, String fieldValue) {
        System.out.println("fieldHasSameValue");
        return false;
    }

    public List<Customer> getAll(byte grade) {
        System.out.println("show customer");
        return null;
    }

    public boolean changeGrade(int customerID, int grade) {
        System.out.println("changeGrade successfully");
        return false;
    }

	@Override
	public boolean add(Customer customer) {
		System.out.println("add successfully");
		return false;
	}

	@Override
	public boolean update(Customer customer) {
		System.out.println("update customer");
		return false;
	}

	@Override
	public boolean hasSame(String field, String value) {
		System.out.println("hasSame field");
		return false;
	}
}
