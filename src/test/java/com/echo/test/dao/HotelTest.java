package com.echo.test.dao;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.echo.dao.hoteldao.HotelDAOImpl;
import com.echo.dao.roomdao.RoomDAOImpl;
import com.echo.domain.po.District;
import com.echo.domain.po.Hotel;
import com.echo.domain.vo.HotelSearchResult;
import com.echo.domain.vo.HotelSearcher;
import com.echo.service.hotelservice.HotelServiceImpl;

/**
 * Hotel DAO Test
 * @author lenovo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class HotelTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	public HotelDAOImpl hotelDAOImpl;
	
	@Test
	public void testAddHotel(){
		Hotel hotel = new Hotel();
		hotel.setHotelName("TestHotelName");
		hotel.setCity("上海");
		hotel.setDistrict("陆家嘴");
		assertTrue((hotelDAOImpl.add(hotel)));
		assertThat(hotelDAOImpl.addReturnID(hotel),greaterThan(0));
	}
	
	@Test
	public void testUpdateHotel(){
		Hotel hotel = new Hotel("TestHotelName_", "上海", "陆家嘴", (byte)5);
		hotel.setHotelID(14);
		assertTrue(hotelDAOImpl.update(hotel));
	}
	
	@Test
	public void testGetHotel(){
		Hotel hotel = hotelDAOImpl.get(1);
		assertNotNull(hotel);
		hotel = hotelDAOImpl.get("南京金陵饭店");
		assertNotNull(hotel);
	}
	
	@Test
	public void testGetHotels1(){
		assertThat( hotelDAOImpl.getHotelsByCity("南京").size(),greaterThan(0));
		assertThat( hotelDAOImpl.getHotelsByDistrict("新街口地区（市中心）").size(),greaterThan(0));
	}
	

	
	@Test
	public void testGetHotels2(){
		List<Hotel> results = hotelDAOImpl.getHotels("南京", "新街口地区（市中心）", (byte)5, "");
		assertThat(results.size(),greaterThan(0));
	}
	
	@Test
	public void testGetCitiesAndDistricts(){
		assertThat(hotelDAOImpl.getCities().size(),greaterThan(0));
		assertThat(hotelDAOImpl.getDistricts("南京").size(),greaterThan(0));
	}
	
	
	@Test
	public void testCheckRemainRoomNumber(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String checkin  = "2016-12-21";
		String checkout  = "2016-12-24";
		Date checkindate = null;
		Date checkoutdate = null;
		try {
			 checkindate = sdf.parse(checkin);
			 checkoutdate = sdf.parse(checkout);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		boolean flag = hotelDAOImpl.checkRemainRoomNumber(checkindate, checkoutdate, 4,15,7);
		assertTrue(flag);
	}
	
	

}
