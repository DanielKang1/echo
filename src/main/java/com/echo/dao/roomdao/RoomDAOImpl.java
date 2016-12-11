package com.echo.dao.roomdao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.echo.domain.po.Room;
import com.echo.domain.po.RoomCheckItem;
import com.echo.domain.po.RoomType;

@Repository
public class RoomDAOImpl implements RoomDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean add(Room room) {
		int result = (int) getSession().save(room);
		if(result > 0 ){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		String hql = "DELETE FROM Room r WHERE r.id = :roomID";
		Query query = getSession().createQuery(hql).setInteger("roomID", id);
		try {
			query.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	@Override
	public boolean update(Room room) {
		try {
			getSession().update(room);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Room get(int id) {
		String hql = "FROM Room WHERE id = ?";   
		Query query = getSession().createQuery(hql);
		List<Room> result = query.setInteger(0,id).list();
		Room room = null;
		if(result.size() > 0 ){
			room = result.get(0);
		}
		return room;
	}
	

	@Override
	public Room get(int hotelID, int roomNumber) {
		String hql = "FROM Room WHERE hotelID = ? AND  roomNumber = ?";   
		Query query = getSession().createQuery(hql);
		List<Room> result = query.setInteger(0,hotelID).setInteger(1, roomNumber).list();
		Room room = null;
		if(result.size() > 0 ){
			room = result.get(0);
		}
		return room;
	}
	
	@Override
	public boolean updateRoomsInfo(int roomTypeID,double price,String typeName){
		String sql = "UPDATE Room SET price = ? , type_name = ? WHERE type_id = ?";
		Query query = getSession().createSQLQuery(sql);
		if(query.setDouble(0, price).setString(1, typeName).setInteger(2, roomTypeID).executeUpdate() > 0){
			return true;
		}
		return false;
	}

	@Override
	public List<Room> getAll(int hotelID) {
		String hql = "FROM Room WHERE hotelID = ?";   
		Query query = getSession().createQuery(hql);
		List<Room> result = query.setInteger(0,hotelID).list();
		return result;
	}

	@Override
	public List<Room> getRoomsByTypeAndStatus( int typeID,byte status) {
		String hql = "FROM Room WHERE typeID = ? AND status = ?";   
		Query query = getSession().createQuery(hql);
		List<Room> result = query.setInteger(0,typeID).setByte(1,status).list();
		return result;
	}
	
	@Override
	public List<Room> getRoomsByStatus(int hotelID, byte status) {
		String hql = "FROM Room WHERE hotelID = ? AND status = ?";   
		Query query = getSession().createQuery(hql);
		List<Room> result = query.setInteger(0,hotelID).setByte(1,status).list();
		return result;
	}

	@Override
	public List<Object[]> getRoomsByPrice(int hotelID, double floor, double ceiling) {
		String sql = "SELECT type_id ,type_name, price , count(*) FROM room WHERE hotel_id = ? AND price >= ? AND price <= ? GROUP BY type_id";   
		Query query = getSession().createSQLQuery(sql);
		List<Object[]> result = query.setInteger(0,hotelID).setDouble(1,floor).setDouble(2,ceiling).list();
		return result;
	}
	

	@Override
	public boolean add(RoomCheckItem roomCheckItem) {
		int result = (int) getSession().save(roomCheckItem);
		if(result > 0 ){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean update(RoomCheckItem roomCheckItem) {
		try {
			getSession().update(roomCheckItem);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public RoomCheckItem getCheckItemByItemID(int id) {
		String hql = "FROM RoomCheckItem WHERE id = ?";   
		Query query = getSession().createQuery(hql);
		List<RoomCheckItem> result = query.setInteger(0,id).list();
		RoomCheckItem roomCheckItem = null;
		if(result.size() > 0 ){
			roomCheckItem = result.get(0);
		}
		return roomCheckItem;
	}
	
	@Override
	public List<RoomCheckItem> getCheckItemList(int hotelID,int roomNumber) {
		String hql = "FROM RoomCheckItem WHERE roomNumber = ? AND hotelID = ?";   
		Query query = getSession().createQuery(hql);
		List<RoomCheckItem> result = query.setInteger(0,roomNumber).setInteger(1,hotelID).list();
		return result;
	}
	
	@Override
	public List<RoomCheckItem> getCheckItemList(int hotelID){
		String hql = "FROM RoomCheckItem WHERE  hotelID = ? AND checkOutDate IS NULL ";   
		Query query = getSession().createQuery(hql);
		List<RoomCheckItem> result = query.setInteger(0,hotelID).list();
		return result;
	}


	@Override
	public boolean add(RoomType roomType) {
		int result = (int) getSession().save(roomType);
		if(result > 0 ){
			return true;
		}
		else{
			return false;
		}
	}
	


	@Override
	public boolean update(RoomType roomType) {
		try {
			getSession().update(roomType);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public RoomType getType(int roomTypeID){
		String hql = "FROM RoomType WHERE typeID = ?";   
		Query query = getSession().createQuery(hql);
		List<RoomType> result = query.setInteger(0,roomTypeID).list();
		RoomType type = null;
		if(result.size() > 0 ){
			type = result.get(0);
		}
		return type;
	}

	@Override
	public List<RoomType> getAllType(int hotelID) {
		String hql = "FROM RoomType WHERE hotelID = ?";   
		Query query = getSession().createQuery(hql);
		List<RoomType> result = query.setInteger(0,hotelID).list();
		return result;
	}

	@Override
	public boolean deleteType(int typeID) {
		String hql = "DELETE FROM RoomType rt WHERE rt.typeID = :typeID";
		Query query = getSession().createQuery(hql).setInteger("typeID", typeID);
		try {
			query.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public double getMinPrice(int hotelID) {
		String sql = "SELECT min(price) FROM roomtype WHERE hotel_id = ?" ;   
		Query query = getSession().createSQLQuery(sql);
		List<BigDecimal> result = query.setInteger(0,hotelID).list();
		System.out.println("-----------------"+hotelID );
		return result.get(0).doubleValue();
	}
	
	/*
	 * 按客房类型ID获得客房数量
	 */
	public int getNumByRoomTypeID(int TypeID){
		String sql = "SELECT count(*) FROM room WHERE type_id = ?" ;   
		Query query = getSession().createSQLQuery(sql);
		List<BigInteger> result = query.setInteger(0,TypeID).list();
		return result.get(0).intValue();
	}



}
