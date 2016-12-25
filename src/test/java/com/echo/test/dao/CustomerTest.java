package com.echo.test.dao;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.echo.dao.customerdao.CustomerDAOimpl;
import com.echo.domain.po.CompanyMember;
import com.echo.domain.po.Customer;
import com.echo.domain.type.MemberLevelType;
import com.echo.utils.EncodeUtils;

/**
 * Customer DAO 测试
 * @author lenovo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "WebContent")
@ContextHierarchy({
        @ContextConfiguration(locations = "classpath:applicationContext.xml"),
})
public class CustomerTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private CustomerDAOimpl customerDAOimpl;
	
	@Test
	public void testGet(){
		assertNotNull(customerDAOimpl.get(3));
		assertNotNull(customerDAOimpl.get("123123"));
	}
	
	@Test
	public void testAdd(){
		Customer cus = new Customer("testNickName", "test@126.com", "qwerty", "13543214321", 0);
		cus.setPwdsalt(EncodeUtils.getSalt(16));
		cus.setGrade(MemberLevelType.Level1);
		assertTrue(customerDAOimpl.add(cus));
	}
	
	@Test
	public void testHashSameName(){
		assertThat(customerDAOimpl.hasSame("nickname","123123"),is(true));
		assertThat(customerDAOimpl.hasSame("nickname","123123",3),is(false));
	}
	
	@Test
	public void testLogin(){
		assertNotNull(customerDAOimpl.get("123@123.com", "123123"));
		assertNotNull(customerDAOimpl.get("123123", "123123"));
	}
	
	@Test
	public void testAddCompanyMember(){
		assertTrue(customerDAOimpl.addCompanyMember(new CompanyMember(1, "网易")));
	}
	
	@Test
	public void testGetCompanyMemberByCID(){
		assertTrue(customerDAOimpl.addCompanyMember(new CompanyMember(1, "网易")));
		assertNotNull(customerDAOimpl.getCompanyMemberByCID(1));
	}
	
	

}
