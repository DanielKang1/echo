	package com.echo.service.test;

import com.echo.domain.po.HotelStaff;
import com.echo.service.hotelstaffservice.HotelStaffService;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class HotelStaffService_stub implements HotelStaffService {

    public boolean addStaff(HotelStaff staff) {
        System.out.println("addStaff successfully");
        return false;
    }

    public HotelStaff getBasicInfo(int staffID) {
        System.out.println("show getBasicInfo");
        return null;
    }

    public boolean updateStaff(HotelStaff staff) {
        System.out.println("updateStaff successfully");
        return false;
    }

	@Override
	public HotelStaff login(String fieldValue, String pwd) {
		 System.out.println("login successfully");
		return null;
	}

	@Override
	public boolean addStaff(int hotelID, String phone, String staffName, String pwd) {
		 System.out.println("addStaff successfully");
		return false;
	}
}
