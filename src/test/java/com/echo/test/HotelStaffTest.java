package com.echo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.echo.dao.hotelstaffdao.HotelStaffDAOImpl;
import com.echo.domain.po.HotelStaff;
import com.echo.service.hotelstaffservice.HotelStaffServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "WebContent")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath:applicationContext.xml"),
        @ContextConfiguration(name = "child", locations = "classpath:springmvc.xml")
})
public class HotelStaffTest {
	
	@Autowired 
	public HotelStaffServiceImpl hotelStaffServiceImpl;
	
	@Autowired 
	public HotelStaffDAOImpl hotelStaffDAOImpl;
	
	@Test
	public void testAddStaff(){
		System.out.println(hotelStaffServiceImpl.addStaff(1, "13412341234", "张一一", "13412341234"));
	}
	
	@Test
	public void testGetStaff1(){
		HotelStaff  staff = hotelStaffDAOImpl.get(1000);
		System.out.println(staff);
	}
	
	@Test
	public void testGetStaff2(){
		HotelStaff  staff = hotelStaffDAOImpl.get("张一一", "13412341234");
		System.out.println(staff);
	}

}
