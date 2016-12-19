package com.echo.dao.customerdao;

import java.util.List;

import com.echo.domain.po.Customer;

public interface CustomerDAO {
	
	public Customer get(int customer_id);
	
	public Customer get(String name);
	
	//用于登录
	public Customer get(String value,String pwd);
	
	public boolean add(Customer customer);
	
	public boolean update(Customer customer);
	/**
	 * field为Customer的属性，可选项：nickname,phone,email
	 */
	public boolean hasSame(String field, String value);
	
	
	 
}
