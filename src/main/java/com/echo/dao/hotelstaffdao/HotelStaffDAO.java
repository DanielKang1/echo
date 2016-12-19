package com.echo.dao.hotelstaffdao;

import com.echo.domain.po.HotelStaff;

public interface HotelStaffDAO {
	
	/**
	 * 通过ID获取HotelStaff对象
	 * @param staffID
	 * @return
	 */
	public HotelStaff get(int staffID);

	public HotelStaff getByHotelID(int hotelID);
	
	public HotelStaff get(String name);
	
	/**
	 * 用于酒店工作人员的登录
	 * @param fieldValue 输入值为Name/Phone/ID中的一种
	 * @param pwd  输入的pwd
	 * @return 
	 */
	public HotelStaff get(String fieldValue,String pwd);
	
	/**
	 * 更新酒店工作人员信息
	 * @param hotelStaff
	 * @return
	 */
	public boolean update(HotelStaff hotelStaff);
	
	/**
	 * 添加新的酒店工作人员
	 * @param hotelStaff
	 * @return
	 */
	public boolean add(HotelStaff hotelStaff);

}
