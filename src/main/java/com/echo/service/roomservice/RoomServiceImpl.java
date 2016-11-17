package com.echo.service.roomservice;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.echo.dao.roomdao.RoomDAOImpl;
import com.echo.domain.po.Order;
import com.echo.domain.po.Room;
import com.echo.domain.po.RoomCheckItem;
import com.echo.domain.po.RoomType;
import com.echo.domain.type.RoomStatusType;

@Service
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	public RoomDAOImpl roomDAOImpl;
	
	@Override
	public RoomType getRoomTypeByTypeID(int roomTypeID){
		return roomDAOImpl.getType(roomTypeID);
	}
	
	@Override
	public List<RoomType> getRoomTypeByHotelID(int hotelID){
		return roomDAOImpl.getAllType(hotelID);
	}
	
	@Override
	public boolean canDeleteRoomType(int typeID){
		List<Room> res1 = roomDAOImpl.getRoomsByTypeAndStatus(typeID, RoomStatusType.FREE);
		List<Room> res2 = roomDAOImpl.getRoomsByTypeAndStatus(typeID, RoomStatusType.USING);
		boolean flag1 = (res1 != null && res1.size() > 0); 
		boolean flag2 = (res2 != null && res2.size() > 0); 
		if(flag1 || flag2){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean updateRoomsInfo(int roomTypeID,double price,String typeName){
		return roomDAOImpl.updateRoomsInfo(roomTypeID, price,typeName);
	}
	
	@Override
	public boolean addRoomType(RoomType roomType) {
		return roomDAOImpl.add(roomType);
	}
	
	@Override
	public boolean deleteRoomType(int TypeID) {
		return roomDAOImpl.deleteType(TypeID);
	}
	
	@Override
	public boolean updateRoomType(RoomType roomType) {
		return roomDAOImpl.update(roomType);
	}
	
	@Override
	public Room getRoomByRoomMumber(int hotelID,int roomNumber) {
		return roomDAOImpl.get(hotelID, roomNumber);
	}
	
	@Override
	public boolean addNewRoom(Room room) {
		return roomDAOImpl.add(room);
	}

	@Override
	public boolean deleteRoom(int id) {
		return roomDAOImpl.delete(id);
	}

	@Override
	public boolean updateRoom(Room room) {
		return roomDAOImpl.update(room);
	}

	@Override
	public List<Room> showEmptyRooms(int hotelid) {
		return roomDAOImpl.getRoomsByStatus(hotelid,RoomStatusType.FREE);
	}

	@Override
	public List<Room> showUsedRooms(int hotelid) {
		return roomDAOImpl.getRoomsByStatus(hotelid,RoomStatusType.USING);
	}

	@Override
	public List<Room> checkIn(int roomTypeID,int num){
		List<Room> availableRooms =  roomDAOImpl.getRoomsByTypeAndStatus(roomTypeID, RoomStatusType.FREE);
		List<Room> result = availableRooms.subList(0, num);
		for(Room room:result){
			room.setStatus(RoomStatusType.USING);
			roomDAOImpl.update(room);
		}
		return result;
		
	}
	
	@Override
	public boolean checkInOffline(int hotrlID,int roomNumber) {
		Room room = roomDAOImpl.get(hotrlID,roomNumber);
		if(room == null){
			return false;
		}
		room.setStatus(RoomStatusType.USING);
		return roomDAOImpl.update(room);
	}

	@Override
	public boolean checkOutOffline(int hotrlID,int roomNumber) {
		Room room = roomDAOImpl.get(hotrlID,roomNumber);
		if(room == null){
			return false;
		}
		room.setStatus(RoomStatusType.FREE);
		return roomDAOImpl.update(room);
	}

	@Override
	public boolean checkOut(int roomCheckItemID) {
		RoomCheckItem item = roomDAOImpl.getCheckItemByItemID(roomCheckItemID);
		if(item == null){
			return false;
		}
		Room room = roomDAOImpl.get(item.getHotelID(),item.getRoomNumber());
		room.setStatus(RoomStatusType.FREE);
		return roomDAOImpl.update(room);
	}
	
	@Override
	public boolean addRoomCheckinItem(Order order,List<Room> rooms){
		RoomCheckItem item = new RoomCheckItem();
		item.setHotelID(order.getHotel().getHotelID());
		item.setOrderID(order.getOrderID());
		item.setUserID(order.getCustomerID());
		try {
			for(int i = 0;i < rooms.size(); i++){
				item.setRoomNumber(rooms.get(i).getRoomNumber());
				roomDAOImpl.add(item);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean addRoomCheckinItemOffline(int hotelID,int roomNumber){
		RoomCheckItem item = new RoomCheckItem();
		item.setHotelID(hotelID);
		item.setUserID(0);
		item.setOrderID(0);
		item.setRoomNumber(roomNumber);
		return roomDAOImpl.add(item);
	}
	
	@Override
	public RoomCheckItem getRoomCheckItemByRoomNumber(int hotelID,int roomNumber) {
		List<RoomCheckItem> list =  roomDAOImpl.getCheckItemList(hotelID, roomNumber);
		RoomCheckItem target = null;
		for(RoomCheckItem roomCheckItem : list){
			if(roomCheckItem.getCheckOutDate() == null)
				target = roomCheckItem;
			break;
		}
		return target;
	}
	
	@Override
	public boolean updateRoomCheckinItem(int roomCheckItemID){
		RoomCheckItem item = roomDAOImpl.getCheckItemByItemID(roomCheckItemID);
		item.setCheckOutDate(new Date());
		return roomDAOImpl.update(item);
	}


	@Override
	public List<RoomCheckItem> getNotCheckoutRoomCheckItems(int hotelID){
		return roomDAOImpl.getCheckItemList(hotelID);
	}



	
}
