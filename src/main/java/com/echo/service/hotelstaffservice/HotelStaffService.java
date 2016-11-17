package com.echo.service.hotelstaffservice;

import com.echo.domain.po.HotelStaff;

public interface HotelStaffService {
	
	/**
	 * 用户登录
	 * @param fieldValue ID/Name/Phone 中的一种
	 * @param pwd
	 * @return
	 */
	public HotelStaff login(String fieldValue,String pwd);
	
	/**
	 * 添加酒店工作人员
	 * @param staff
	 * @return
	 */
	public boolean addStaff(int hotelID,String phone,String staffName,String pwd);
	
	/**
	 * 获得工作人员基础信息
	 * @param staffID
	 * @return
	 */
	public HotelStaff getBasicInfo(int staffID);

	/**
	 * 更新酒店工作人员的信息
	 * @param staff
	 * @return
	 */
	public boolean updateStaff(HotelStaff staff);
}
