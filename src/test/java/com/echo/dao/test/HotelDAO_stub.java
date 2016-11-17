package com.echo.dao.test;

import java.util.List;

import com.echo.dao.hoteldao.HotelDAO;
import com.echo.domain.po.Hotel;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class HotelDAO_stub implements HotelDAO {
    public boolean add(Hotel hotel) {
        System.out.println("add successfully");
        return false;
    }

    public boolean update(Hotel hotel) {
        System.out.println("update successfully");
        return false;
    }

    public Hotel get(int hotelID) {
        System.out.println("show hotel");
        return null;
    }

    public List<Object[]> getRoomTypeAndNum(int hotelID) {
        System.out.println("show roomtypeandnum");
        return null;
    }

    public List<Integer> getHotelIDs(String city, String district, String hotelName, String starLevel, String keyWord) {
        System.out.println("show hotelid");
        return null;
    }

	@Override
	public List<Hotel> getHotels(String city, String district, byte starLevel, String keyWord) {
		System.out.println("getHotels successfully");
		return null;
	}
}
