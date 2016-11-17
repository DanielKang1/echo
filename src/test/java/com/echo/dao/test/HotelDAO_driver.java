package com.echo.dao.test;

import com.echo.dao.hoteldao.HotelDAO;
import com.echo.domain.po.Hotel;


/**
 * Created by D.niel_K on 16/10/15.
 */
public class HotelDAO_driver {
    public void drive(HotelDAO hotelDAO) {
       hotelDAO.get(0);
        hotelDAO.add(new Hotel());
        hotelDAO.getRoomTypeAndNum(0);
        hotelDAO.update(new Hotel());

    }

    public void main(String[] args){
        (new HotelDAO_driver()).drive(new HotelDAO_stub()) ;
    }
}
