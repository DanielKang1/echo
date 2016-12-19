package com.echo.test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.echo.dao.customerdao.CustomerDAOimpl;

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
		System.out.println(customerDAOimpl.get(1));
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("");
		Resource template = ctx.getResource("http://myhost.com/resource/path/myTemplate.txt");
	}

}
