package com.echo.dao.hoteldao;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.echo.domain.po.District;
import com.echo.domain.po.Hotel;
import com.echo.utils.DateUtils;

@Repository
public class HotelDAOImpl implements HotelDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean add(Hotel hotel) {
		int result = (int) getSession().save(hotel);
		if(result > 0 ){
			return true;
		}
		else{
			return false;
		}
	}
	
	public int addReturnID(Hotel hotel) {
		return (int) getSession().save(hotel);
	}

	@Override
	public boolean update(Hotel hotel) {
		try {
			getSession().update(hotel);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Override
	public Hotel get(String hotelName) {
		String hql = "FROM Hotel WHERE hotelName = ?";   
		Query query = getSession().createQuery(hql);
		List<Hotel> result = query.setString(0,hotelName).list();
		Hotel hotel = null;
		if(result.size() > 0 ){
			hotel = result.get(0);
		}
		return hotel;
	}

	@Override
	public Hotel get(int hotelID) {
		String hql = "FROM Hotel WHERE hotelID = ?";   
		Query query = getSession().createQuery(hql);
		List<Hotel> result = query.setInteger(0,hotelID).list();
		Hotel hotel = null;
		if(result.size() > 0 ){
			hotel = result.get(0);
		}
		return hotel;
	}

	@Override
	public List<Object[]> getRoomTypeAndNum(int hotelID) {
		return null;
	}

	@Override
	public List<Hotel> getHotels(String city, String district, byte starLevel, String keyWord) {
		
		List<Hotel> result = null;
		String hql = null;
		if(starLevel != 0){
			hql = "FROM Hotel WHERE city LIKE ? AND district LIKE ?  "
					+ "AND starLevel = ? AND ( briefIntro LIKE ? OR facility LIKE ? OR hotelName LIKE ? )";   
			Query query = getSession().createQuery(hql);
			result = query.setString(0,"%"+city+"%")
					.setString(1, "%"+district+"%")
					.setByte(2, starLevel)
					.setString(3, "%"+keyWord+"%")
					.setString(4, "%"+keyWord+"%")
					.setString(5, "%"+keyWord+"%")
					.list();
		}else{
			 hql = "FROM Hotel WHERE city LIKE ? AND district LIKE ? AND ( briefIntro LIKE ? OR facility LIKE ? OR hotelName LIKE ? )"; 
			 Query query = getSession().createQuery(hql);
				result = query.setString(0,"%"+city+"%")
						.setString(1, "%"+district+"%")
						.setString(2, "%"+keyWord+"%")
						.setString(3, "%"+keyWord+"%")
						.setString(4, "%"+keyWord+"%")
						.list();
		}
		
		return result;
	}
	
	
	public List<District> getDistricts(String city){
		String hql = "FROM District WHERE city_name = ?";   
		Query query = getSession().createQuery(hql);
		List<District> result = query.setString(0,city).list();
		return result;
	}
	
	public List<String> getCities(){
		String sql = "SELECT DISTINCT city_name FROM district";   
		Query query = getSession().createSQLQuery(sql);
		List<String> result = query.list();
		return result;
	}
	
	/**
	 * 用于检查用户预订的房型在预订的时间内是否已经被预订满
	 * @param checkinDate 入住时间
	 * @param checkoutDate 退房时间
	 * @param roomTypeID 客房ID
	 * @param roomNum  已被预订数量
	 * @param roomNeedRoom  用户需要的预订数量
	 * @return true:客房充足，可预订；false:客房数量不足，不可预订。
	 */
	public boolean checkRemainRoomNumber(Date checkinDate,Date checkoutDate ,int roomTypeID,int allRoomNum,int roomNeedRoom){
		boolean flag = true;
		String sql = "SELECT COUNT(*) FROM c_order"
				+ " WHERE ROOM_TYPE_ID = ? "
				+ "AND DATEDIFF(?,CHECKIN_DATE) >= 0 "
				+ "AND DATEDIFF(CHECKOUT_DATE,?) >= 1";
		Query query = getSession().createSQLQuery(sql);
		List<BigInteger> result = null;
		int daysDiff = DateUtils.getDaysDiff(checkoutDate,checkinDate);
		for(int i = 0; i < daysDiff; i++){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(checkinDate);
			calendar.add(Calendar.DAY_OF_MONTH, i);  
			Date flagDate = calendar.getTime();  
			result = query.setInteger(0, roomTypeID).setDate(1,flagDate).setDate(2,flagDate).list();
			if(result.get(0).intValue() + roomNeedRoom > allRoomNum){
				flag = false;
				break;
			}
		}
		
		return flag;
	}
	
	  
}
