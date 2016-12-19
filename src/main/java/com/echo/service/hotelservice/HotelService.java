package com.echo.service.hotelservice;

import java.util.List;

import com.echo.domain.po.Hotel;
import com.echo.domain.po.Room;
import com.echo.domain.vo.HotelSearchResult;
import com.echo.domain.vo.HotelSearcher;
import com.echo.domain.vo.RoomSearchResult;

public interface HotelService {
	
	/**
	 * 录入新酒店
	 * @param hotel
	 * @return
	 */
	public boolean addHotel(Hotel hotel);
	
	/**
	 * 更新酒店信息
	 * @param hotel
	 * @return
	 */
	public boolean updateBasicInfo(Hotel hotel);
	
	/**
	 * 获得酒店的全部房间
	 * @param hotel
	 * @return
	 */
	public List<Room> getAllRooms(int hotelID);
	
	/**
	 * 获取Hotel
	 * @param hotelID
	 * @return
	 */
	public Hotel getHotelByID(int hotelID);
	
	public Hotel getHotelByName(String name);
	
	/**
	 * 按照HotelSearcher进行搜索，获得符合条件的HotelSearchResult(用于用户在网站上的搜索)
	 * @param hotelSearcher
	 * @return
	 */
	public List<HotelSearchResult> search(HotelSearcher hotelSearcher);
	

}
