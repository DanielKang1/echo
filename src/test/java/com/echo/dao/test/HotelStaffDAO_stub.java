package com.echo.dao.test;

import com.echo.dao.hotelstaffdao.HotelStaffDAO;
import com.echo.domain.po.HotelStaff;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class HotelStaffDAO_stub implements HotelStaffDAO {
    public HotelStaff get(int staffID) {
        System.out.println("show hotelstaff");
        return null;
    }

    public HotelStaff get(String fieldValue, String pwd) {
        System.out.println("show hotelstaff");
        return null;
    }

    public boolean update(HotelStaff hotelStaff) {
        System.out.println("update successfully");
        return false;
    }

	@Override
	public boolean add(HotelStaff hotelStaff) {
		System.out.println("add successfully");
		return false;
	}
}
