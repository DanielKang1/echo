package com.echo.test.service.mock;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.echo.dao.hoteldao.HotelDAOImpl;
import com.echo.dao.roomdao.RoomDAOImpl;
import com.echo.domain.po.District;
import com.echo.domain.po.Hotel;
import com.echo.domain.vo.HotelSearchResult;
import com.echo.domain.vo.HotelSearcher;
import com.echo.domain.vo.RoomSearchResult;
import com.echo.service.hotelservice.HotelServiceImpl;

public class HotelTest {
	
	@InjectMocks
	private HotelServiceImpl mockHotelServiceImpl;
	
	@Mock
	private HotelDAOImpl mockHotelDAOImpl;
	
	@Mock
	private RoomDAOImpl mockRoomDAOImpl;
	
	@Before 
    public void initMocks() {
    	//初始化当前测试类所有Mock模拟对象
        MockitoAnnotations.initMocks(this);
    }
	
	/**
	 * 测试获得城市与商圈
	 */
	@Test
	public void testGetDistricts(){
		List<String> cities = new ArrayList<>();
		cities.add("南京");
		cities.add("北京");
		cities.add("上海");
		List<District> districts = new ArrayList<>();
		District district = new District();
		district.setDistrictName("新街口");
		districts.add(district);
		
		when(mockHotelDAOImpl.getCities()).thenReturn(cities);
		when(mockHotelDAOImpl.getDistricts("南京")).thenReturn(districts);
		assertThat(mockHotelServiceImpl.getCities(), hasItem("南京"));
		assertThat(mockHotelServiceImpl.getDistrictsByCity("南京"), hasItem(district));
		verify(mockHotelDAOImpl,times(1)).getCities();
		verify(mockHotelDAOImpl,times(1)).getDistricts("南京");
	}
	
	
	/**
	 * 测试搜索酒店与房间（显示酒店信息以及酒店满足价格要求的房间信息）
	 */
	@Test
	public void testSearch(){
		HotelSearcher hotelSearcher = new HotelSearcher("南京", "新街口", (byte)5, "", 400, 1000);
		Hotel hotel1 = new Hotel(1, "南京金陵饭店", "南京", "新街口", (byte)5);
		Hotel hotel2 = new Hotel(2, "南京玄武饭店", "南京", "新街口", (byte)5);
		List<Hotel> hotels = new ArrayList<>();
		hotels.add(hotel1);
		hotels.add(hotel2);
		List<Object[]> roomInfo1 = new ArrayList<>();
		roomInfo1.add( new Object[]{1,"豪华房",233,34});
		roomInfo1.add( new Object[]{2,"普通房",688,23});
		List<Object[]> roomInfo2 = new ArrayList<>();
		roomInfo2.add( new Object[]{1,"A套房",433,14});
		roomInfo2.add( new Object[]{2,"B套房",788,23});
		when(mockHotelDAOImpl.getHotels("南京", "新街口", (byte)5, "")).thenReturn(hotels);
		when(mockRoomDAOImpl.getMinPrice(1)).thenReturn(233.0);
		when(mockRoomDAOImpl.getMinPrice(2)).thenReturn(233.0);
		when(mockRoomDAOImpl.getRoomsByPrice(1,400,1000)).thenReturn(roomInfo1);
		when(mockRoomDAOImpl.getRoomsByPrice(2,400,1000)).thenReturn(roomInfo2);
		
		List<HotelSearchResult> results = mockHotelServiceImpl.search(hotelSearcher);
		int roomNums = 0;
		assertThat(results.size(),is(2));
		for(HotelSearchResult result :results){
			roomNums += result.getRoomInfo().size();
		}
		assertThat(roomNums,is(3));  //符合条件的房间有3种
		
		verify(mockHotelDAOImpl,times(1)).getHotels(anyString(),anyString(),any(byte.class),anyString());
		verify(mockRoomDAOImpl,times(2)).getMinPrice( any(int.class) );
		verify(mockRoomDAOImpl,times(2)).getRoomsByPrice( any(int.class),any(double.class),any(double.class) );
	}
	
	/**
	 * 测试管理员添加酒店与修改酒店
	 */
	@Test
	public void testHotelHandle(){
		Hotel hotel1 = new Hotel(1, "南京金陵饭店", "南京", "新街口", (byte)5);
		when(mockHotelDAOImpl.add(hotel1)).thenReturn(true);
		when(mockHotelDAOImpl.update(hotel1)).thenReturn(true);
		
		assertTrue(mockHotelServiceImpl.addHotel(hotel1));
		hotel1.setBriefIntro("brief intro");
		assertTrue(mockHotelServiceImpl.updateBasicInfo(hotel1));
		assertThat(hotel1.getBriefIntro(),equalTo("brief intro"));
		
		verify(mockHotelDAOImpl,times(1)).add(any(Hotel.class) );
		verify(mockHotelDAOImpl,times(1)).update(any(Hotel.class) );
		
	}

}
