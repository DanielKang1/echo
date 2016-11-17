package com.echo.service.test;

import com.echo.domain.po.HotelStaff;
import com.echo.service.hotelstaffservice.HotelStaffService;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class HotelStaffService_driver {
    public void drive(HotelStaffService hotelStaffService){
        hotelStaffService.getBasicInfo(0);
        hotelStaffService.login("abc","123");
        hotelStaffService.updateStaff(new HotelStaff());
    }

    public static void main(String[] args) {
        (new HotelStaffService_driver()).drive(new HotelStaffService_stub());
    }
}
