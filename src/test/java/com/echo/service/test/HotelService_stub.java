package com.echo.service.test;

import java.util.List;

import com.echo.domain.po.Hotel;
import com.echo.domain.po.Room;
import com.echo.domain.vo.HotelSearchResult;
import com.echo.domain.vo.HotelSearcher;
import com.echo.domain.vo.RoomSearchResult;
import com.echo.service.hotelservice.HotelService;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class HotelService_stub implements HotelService {
    public boolean addHotel(Hotel hotel) {
        System.out.println("addHotel successfully");
        return false;
    }

    public boolean updateBasicInfo(Hotel hotel) {
        System.out.println("updateBasicInfo successfully");
        return false;
    }

    public List<RoomSearchResult> getRoomSearchResults(int hotelID) {
        System.out.println("show RoomSearchResult");
        return null;
    }

    public List<RoomSearchResult> getRoomSearchResultsByNum(int hotelID, int num) {
        System.out.println("show RoomSearchResult");
        return null;
    }

    public List<HotelSearchResult> search(HotelSearcher hotelSearcher) {
        System.out.println("show RoomSearchResult");
        return null;
    }

	@Override
	public List<Room> getAllRooms(int hotelID) {
		System.out.println("getAllRooms successfully");
		return null;
	}

	@Override
	public Hotel getHotelByID(int hotelID) {
		System.out.println("getHotelByID successfully");
		return null;
	}
 
}
