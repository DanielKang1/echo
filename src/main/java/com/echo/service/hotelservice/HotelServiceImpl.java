package com.echo.service.hotelservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.echo.dao.hoteldao.HotelDAOImpl;
import com.echo.dao.roomdao.RoomDAOImpl;
import com.echo.domain.po.District;
import com.echo.domain.po.Hotel;
import com.echo.domain.po.Room;
import com.echo.domain.vo.HotelSearchResult;
import com.echo.domain.vo.HotelSearcher;
import com.echo.domain.vo.RoomSearchResult;

@Service
public class HotelServiceImpl implements HotelService{
	
	@Autowired
	public HotelDAOImpl hotelDAOImpl;
	
	@Autowired
	public RoomDAOImpl roomDAOImpl;
	
	
	
	public List<District> getDistrictsByCity(String city){
		return hotelDAOImpl.getDistricts(city);
	}
	
	public List<String> getCities(){
		return hotelDAOImpl.getCities();
	}

	@Override
	public boolean addHotel(Hotel hotel) {
		return hotelDAOImpl.add(hotel);
	}
	
	public int addHotelReturnID(Hotel hotel) {
		return hotelDAOImpl.addReturnID(hotel);
	}

	@Override
	public boolean updateBasicInfo(Hotel hotel) {
		return hotelDAOImpl.update(hotel);
	}


	/**
	 * 搜索操作，返回以HotelSearchResult为结果的List
	 */
	@Override
	public List<HotelSearchResult> search(HotelSearcher hotelSearcher) {
		List<HotelSearchResult> results = new ArrayList<>();
		List<Hotel> hotels = hotelDAOImpl.getHotels(hotelSearcher.getCity(), hotelSearcher.getDistrict(), hotelSearcher.getStarLevel(), hotelSearcher.getKeyWord());
		for(int i = 0 ;i < hotels.size() ; i++){
			HotelSearchResult res = new HotelSearchResult();
			Hotel hotel = hotels.get(i);
			res.setHotel(hotel);
			res.setMinPrice(roomDAOImpl.getMinPrice(hotel.getHotelID()));
			res.setRoomInfo(getRoomsByPrice(hotel,hotelSearcher.getPriceFloor(),hotelSearcher.getPriceCeiling()));
			results.add(res);
		}
		return results;
	}
	
	/**
	 * 搜索酒店满足价格规定的房间
	 */
	private List<RoomSearchResult> getRoomsByPrice(Hotel hotel, double floor, double ceiling){
		List<RoomSearchResult> roomResults = new ArrayList<>();
		List<Object[]> roomInfo = roomDAOImpl.getRoomsByPrice(hotel.getHotelID(), floor, ceiling);
		for(int i = 0; i < roomInfo.size(); i++){
			Object [] tmp = roomInfo.get(i);
			double price = Double.parseDouble(tmp[2].toString());
			if(price >= floor && price <= ceiling){
				int roomTypeID = Integer.parseInt(tmp[0].toString());
				String roomTypeName = tmp[1].toString();
				int roomNum = Integer.parseInt(tmp[3].toString());
				RoomSearchResult res = new RoomSearchResult(roomTypeID, roomTypeName, price, roomNum);
				roomResults.add(res);
			}
		}
		return roomResults;
	}

	@Override
	public Hotel getHotelByID(int hotelID) {
		return hotelDAOImpl.get(hotelID);
	}
	
	public Hotel getHotelByName(String hotelName){
		return hotelDAOImpl.get(hotelName);
	}

	@Override
	public List<Room> getAllRooms(int hotelID) {
		return roomDAOImpl.getAll(hotelID);
	}
	

	/**
	 * 检查在某段时间内某个型号的客房是否允许预订
	 * @param checkinDate 入住时间
	 * @param checkoutDate 退房时间
	 * @param roomTypeID 客房ID
	 * @param allRoomNum  该房型的客房总量
	 * @param roomNeedRoom 需要的数量
	 * @return true:允许预订；false:不允许预订
	 */
	public boolean  allowBookingOrNot(Date checkinDate,Date checkoutDate ,int roomTypeID,int roomNeedRoom){
		return hotelDAOImpl.checkRemainRoomNumber(checkinDate,checkoutDate,roomTypeID,roomDAOImpl.getNumByRoomTypeID(roomTypeID),roomNeedRoom);
	}
}
