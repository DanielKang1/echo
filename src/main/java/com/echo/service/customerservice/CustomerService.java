package com.echo.service.customerservice;

import org.springframework.validation.BindingResult;

import com.echo.domain.po.Customer;

public interface CustomerService {
	
	/**
	 * 对用户信息进行加密
	 */
	public void encodeCustomer(Customer customer);
	
	/**
	 * 对用户信息进行解密
	 */
	public void decodeCustomer(Customer customer);
	
	/**
	 * 用户参数格式验证，包含手机格式，密码，用户名
	 */
	public boolean validator(Customer customer, BindingResult result);
	
	public boolean register(Customer customer);
	
	public Customer login(String value, String pwd);
	
	public boolean modifyInfo(Customer customer);
	
	public boolean modifyPwd(Customer customer,String newPwd);
	
	public boolean modifyCredit(Customer customer,double amount);
	
	/**
	 * 获得基础信息
	 * @param custoermID
	 * @return
	 */
	public Customer getBasicInfo(int custoermID);
	
	public Customer getBasicInfo(String name);
	
	/**
	 * 普通用户注册为普通会员
	 * @param customerID
	 * @return
	 */
	public boolean beMember(Customer customer);
	
	public boolean beVIPMember(Customer customer);
	
}
