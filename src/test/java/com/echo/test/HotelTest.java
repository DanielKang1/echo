package com.echo.test;

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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class HotelTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	public HotelDAOImpl hotelDAOImpl;
	
	@Test
	@Rollback
	public void testAddHotel(){
		Hotel hotel = new Hotel();
		hotel.setHotelName("Test测aa");
		hotel.setCity("上海");
		hotel.setDistrict("陆家嘴");
		assertTrue((hotelDAOImpl.add(hotel)));
	}
	
	@Test
	@Rollback
	public void testUpdateHotel(){
		Hotel hotel = new Hotel();
		hotel.setHotelID(8);
		hotel.setHotelName("Test测试酒店233");
		hotel.setCity("南京");
		hotel.setDistrict("新街口地区（市中心）");
		hotel.setAddress("233");
		hotel.setStarLevel((byte) 1);
		hotel.setBriefIntro("233");
		hotel.setFacility("233");
		hotelDAOImpl.update(hotel);
	}
	
	@Test
	public void testGetHotel(){
		Hotel hotel = hotelDAOImpl.get(1);
		assertNotNull(hotel);
//		System.out.println(hotel);
	}
	
	@Test
	public void testGetHotels(){
		List<Hotel> results = hotelDAOImpl.getHotels("南", "", (byte)5, "");
		assertThat(results.size(),greaterThan(0));
//		System.out.println(results.size());
	}
	
	@Test
	public void testCityDistrict(){
		List<District> result = hotelDAOImpl.getDistricts("南京");
		System.out.println(result);
	}
	
	@Autowired
	public HotelServiceImpl hotelServiceImpl;
	
	@Test
	public void testSearch(){
		HotelSearcher hotelSearcher = new HotelSearcher("南京", "新街口", (byte)5, "测试", 100, 900);
		List<HotelSearchResult> res = hotelServiceImpl.search(hotelSearcher);
		System.out.println(res.size());
	}
	
	
	@Autowired
	public RoomDAOImpl roomDAOImpl;
	
	@Test
	public void testCheckRemainRoomNumber(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String checkin  = "2016-11-01";
		String checkout  = "2016-11-04";
		Date checkindate = null;
		Date checkoutdate = null;
		try {
			 checkindate = sdf.parse(checkin);
			 checkoutdate = sdf.parse(checkout);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		boolean flag =hotelDAOImpl.checkRemainRoomNumber(checkindate, checkoutdate, 4,roomDAOImpl.getNumByRoomTypeID(4),7);
		System.out.println(flag);
	}
	

}
