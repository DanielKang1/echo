package com.echo.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.echo.dao.hotelstaffdao.HotelStaffDAOImpl;
import com.echo.domain.po.HotelStaff;
import com.echo.service.hotelstaffservice.HotelStaffServiceImpl;

/**
 * HotelStaff DAO Test
 * @author lenovo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "WebContent")
@ContextHierarchy({
        @ContextConfiguration(  locations = "classpath:applicationContext.xml")
})
public class HotelStaffTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	
	@Autowired 
	public HotelStaffDAOImpl hotelStaffDAOImpl;
	
	@Test
	public void testAddStaff(){
		HotelStaff hotelStaff = new HotelStaff("张一一", 1, "13412341234", "13412341234");
		assertTrue(hotelStaffDAOImpl.add(hotelStaff));
	}
	
	@Test
	public void testGetStaff1(){
		HotelStaff  staff = hotelStaffDAOImpl.get(1001);
		assertNotNull(staff);
	}
	
	@Test
	public void testGetStaff2(){
		HotelStaff  staff = hotelStaffDAOImpl.get("13412341234", "13412341234");
		assertNotNull(staff);
	}
	
	@Test
	public void testUpdateStaff(){
		HotelStaff  staff = hotelStaffDAOImpl.get(1001);
		staff.setStaffName("testNewName");
		assertTrue(hotelStaffDAOImpl.update(staff));
	}
	
	
	
	

}
