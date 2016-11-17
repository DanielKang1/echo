package com.echo.test.mock;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.echo.dao.hotelstaffdao.HotelStaffDAOImpl;
import com.echo.domain.po.HotelStaff;
import com.echo.service.hotelstaffservice.HotelStaffServiceImpl;


public class HotelStaffTest {
	
	@Mock
	private HotelStaffDAOImpl mockHotelStaffDAOImpl;
	
	@InjectMocks
	private HotelStaffServiceImpl mockHotelStaffServiceImpl;
	
	@Before
	public void initMock(){
		MockitoAnnotations.initMocks(this);
	}
	/**
	 * 测试酒店管理人员的添加、获取、信息修改
	 */
	@Test
	public void testGetAndUpdateStaff(){
		HotelStaff staff = new HotelStaff();
		staff.setHotelID(1);
		staff.setPhone("13412341234");
		staff.setPwd("13412341234");
		staff.setStaffName("南京金陵饭店");
		when(mockHotelStaffDAOImpl.add(any(HotelStaff.class))).thenReturn(true);
		when(mockHotelStaffDAOImpl.update(any(HotelStaff.class))).thenReturn(true);
		when(mockHotelStaffDAOImpl.get(anyInt())).thenReturn(staff);
		assertTrue(mockHotelStaffServiceImpl.addStaff(1, "13412341234", "南京金陵饭店", "13412341234"));
		HotelStaff result = mockHotelStaffServiceImpl.getBasicInfo(10010);
		assertNotNull(result);
		result.setPhone("13212312345");
		assertTrue(mockHotelStaffServiceImpl.updateStaff(staff));
		
		verify(mockHotelStaffDAOImpl,times(1)).get(anyInt());
		verify(mockHotelStaffDAOImpl,times(1)).update(any(HotelStaff.class));
		verify(mockHotelStaffDAOImpl,times(1)).add(any(HotelStaff.class));
	}
	
	/**
	 * 测试酒店管理人员登录
	 */
	@Test
	public void testLogin(){
		HotelStaff staff = new HotelStaff();
		staff.setStaffName("Finch");
		when(mockHotelStaffDAOImpl.get("Finch", "poipoi")).thenReturn(staff);
		when(mockHotelStaffDAOImpl.getPwdsalt(anyString())).thenReturn("");
		when(mockHotelStaffDAOImpl.checkPwd("Finch", "","poipoi")).thenReturn(1);
		when(mockHotelStaffDAOImpl.get(1)).thenReturn(staff);
		HotelStaff result = mockHotelStaffServiceImpl.login("Finch", "poipoi");
		assertNotNull(result);
		assertThat(result.getStaffName(),equalTo("Finch"));
		verify(mockHotelStaffDAOImpl,times(1)).get(anyString(),anyString());
	}
	

}
