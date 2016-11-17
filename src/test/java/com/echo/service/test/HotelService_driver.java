package com.echo.service.test;

import com.echo.domain.po.Hotel;
import com.echo.domain.vo.HotelSearcher;
import com.echo.service.hotelservice.HotelService;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class HotelService_driver {
    public void drive(HotelService hotelService){
        hotelService.addHotel(new Hotel());
        hotelService.search(new HotelSearcher());
        hotelService.updateBasicInfo(new Hotel());
    }

    public static void main(String[] args) {
        (new HotelService_driver()).drive(new HotelService_stub());
    }
}
