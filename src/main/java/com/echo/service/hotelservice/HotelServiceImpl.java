package com.echo.service.hotelservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.echo.service.evaluationservice.EvaluationServiceImpl;
import com.echo.service.orderservice.OrderServiceImpl;

@Service
public class HotelServiceImpl implements HotelService{
	
	@Autowired
	public HotelDAOImpl hotelDAOImpl;
	
	@Autowired
	public RoomDAOImpl roomDAOImpl;
	
	@Autowired
	private EvaluationServiceImpl evaluationServiceImpl;
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	
	
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
			res.setEvalutionNum(evaluationServiceImpl.getHotelEva(hotel.getHotelID()).size());
			res.setRating(evaluationServiceImpl.getAverageRating(hotel.getHotelID()));
			results.add(res);
		}
		return results;
	}
	
	public List<HotelSearchResult> searchByCityName(String cityName){
		List<HotelSearchResult> results = new ArrayList<>();
		List<Hotel> hotels = hotelDAOImpl.getHotelsByCity(cityName);
		for(int i = 0 ;i < hotels.size() ; i++){
			HotelSearchResult res = new HotelSearchResult();
			Hotel hotel = hotels.get(i);
			res.setHotel(hotel);
			res.setMinPrice(roomDAOImpl.getMinPrice(hotel.getHotelID()));
			res.setRoomInfo(getRoomsByPrice(hotel,0));
			res.setEvalutionNum(evaluationServiceImpl.getHotelEva(hotel.getHotelID()).size());
			res.setRating(evaluationServiceImpl.getAverageRating(hotel.getHotelID()));
			results.add(res);
		}
		return results;
	}
	
	public List<HotelSearchResult> searchByDistrictName(String district){
		List<HotelSearchResult> results = new ArrayList<>();
		List<Hotel> hotels = hotelDAOImpl.getHotelsByDistrict(district);
		for(int i = 0 ;i < hotels.size() ; i++){
			HotelSearchResult res = new HotelSearchResult();
			Hotel hotel = hotels.get(i);
			res.setHotel(hotel);
			res.setMinPrice(roomDAOImpl.getMinPrice(hotel.getHotelID()));
			res.setRoomInfo(getRoomsByPrice(hotel,0));
			res.setEvalutionNum(evaluationServiceImpl.getHotelEva(hotel.getHotelID()).size());
			res.setRating(evaluationServiceImpl.getAverageRating(hotel.getHotelID()));
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
	
	private List<RoomSearchResult> getRoomsByPrice(Hotel hotel, double floor){
		List<RoomSearchResult> roomResults = new ArrayList<>();
		List<Object[]> roomInfo = roomDAOImpl.getRoomsByPrice(hotel.getHotelID(), floor);
		for(int i = 0; i < roomInfo.size(); i++){
			Object [] tmp = roomInfo.get(i);
			double price = Double.parseDouble(tmp[2].toString());
			if(price >= floor){
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
	
	public void sortByPriceDescending(List<HotelSearchResult> result){
		Comparator comparator=new Comparator(){
			@Override
			public int compare(Object o1, Object o2) {
				HotelSearchResult r1 = (HotelSearchResult)o1;
				HotelSearchResult r2 = (HotelSearchResult)o2;
				if(r2.getMinPrice() - r1.getMinPrice() < 0) return  -1;
				else return 1;
			}
		};
		 Collections.sort(result, comparator);
	}
	
	public void sortByPriceAscending(List<HotelSearchResult> result){
		Comparator comparator=new Comparator(){
			@Override
			public int compare(Object o1, Object o2) {
				HotelSearchResult r1 = (HotelSearchResult)o1;
				HotelSearchResult r2 = (HotelSearchResult)o2;
				if(r1.getMinPrice() - r2.getMinPrice() < 0) return  -1;
				else return 1;
			}
		};
		 Collections.sort(result, comparator);
	}
	
	public void sortByStarLevelDescending(List<HotelSearchResult> result){
		Comparator comparator=new Comparator(){
			@Override
			public int compare(Object o1, Object o2) {
				HotelSearchResult r1 = (HotelSearchResult)o1;
				HotelSearchResult r2 = (HotelSearchResult)o2;
				if(r2.getHotel().getStarLevel() - r1.getHotel().getStarLevel() < 0) return  -1;
				else return 1;
			}
		};
		 Collections.sort(result, comparator);
	}
	
	public void sortByRatingDescending(List<HotelSearchResult> result){
		Comparator comparator=new Comparator(){
			@Override
			public int compare(Object o1, Object o2) {
				HotelSearchResult r1 = (HotelSearchResult)o1;
				HotelSearchResult r2 = (HotelSearchResult)o2;
				if(r2.getRating() - r1.getRating() < 0) return  -1;
				else return 1;
			}
		};
		 Collections.sort(result, comparator);
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
	
	/**
	 * 只搜索入住过的结果
	 * @param searchResult 完整的搜索结果
	 * @param customerID  用户ID
	 * @return
	 */
	public List<HotelSearchResult> getLivedResult(List<HotelSearchResult> searchResult,int customerID){
		List<HotelSearchResult> res_ = new ArrayList<>();
		Map<Hotel,Integer> staResult = orderServiceImpl.getOrderTimesByHotel(customerID); //订购次数的统计结果
		Iterator it=staResult.keySet().iterator();
		List<Integer> ids = new ArrayList<>();
		while(it.hasNext()){
			ids.add(((Hotel)it.next()).getHotelID());
		}
		for(HotelSearchResult hsr : searchResult){
			if(ids.contains(hsr.getHotel().getHotelID())){
				res_.add(hsr);
			}
		}
		return res_;
	}
}
