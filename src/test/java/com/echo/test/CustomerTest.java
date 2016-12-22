package com.echo.test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.echo.dao.customerdao.CustomerDAOimpl;
import com.echo.domain.po.CompanyMember;
import com.echo.domain.po.Customer;
import com.echo.service.customerservice.CustomerServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "WebContent")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath:applicationContext.xml"),
        @ContextConfiguration(name = "child", locations = "classpath:springmvc.xml")
})
public class CustomerTest {
	
	@Autowired
	private CustomerDAOimpl customerDAOimpl;
	
	@Test
	public void testHashSameName(){
		System.out.println(customerDAOimpl.hasSame("nickname","123123"));
		assertThat(customerDAOimpl.hasSame("nickname","123123"),is(true));
	}
	
	@Test
	public void testLogin(){
//		customerDAOimpl.get("123123", "123123");
		System.out.println(customerDAOimpl.get(3));
	}
	
	@Test
	public void testGet(){
		Customer cus = customerDAOimpl.get(3);
		Customer cus_ = customerDAOimpl.get(3);
	}
	
	@Test
	public void testGet2(){
		Customer cus = customerDAOimpl.get("123123");
		System.out.println(cus);
		Customer cus_ = customerDAOimpl.get("123123");
		System.out.println(cus_);
	}
	
	@Autowired
	private CustomerServiceImpl impl;
	
	@Test
	public void testAddCompanyMember(){
		CompanyMember companyMember = new CompanyMember(3, "万达集团");
		impl.addCompanyMember(companyMember);
	}
	
	@Test
	public void testGetCompanyMember(){
		assertNotNull(impl.getCompanyMemberByCID(3));
	}
	
	

}
