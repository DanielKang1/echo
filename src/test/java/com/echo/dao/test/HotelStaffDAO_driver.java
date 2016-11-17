package com.echo.dao.test;

import com.echo.dao.hotelstaffdao.HotelStaffDAO;
import com.echo.domain.po.HotelStaff;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class HotelStaffDAO_driver {
    public void drive(HotelStaffDAO hotelStaffDAO) {
        hotelStaffDAO.get(0);
        hotelStaffDAO.get("abc","123");
        hotelStaffDAO.add(new HotelStaff());
        hotelStaffDAO.update(new HotelStaff());
    }

    public void main(String[] args){
        (new HotelStaffDAO_driver()).drive(new HotelStaffDAO_stub()) ;
    }
}
