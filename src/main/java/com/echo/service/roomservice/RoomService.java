package com.echo.service.roomservice;

import java.util.List;

import com.echo.domain.po.Order;
import com.echo.domain.po.Room;
import com.echo.domain.po.RoomCheckItem;
import com.echo.domain.po.RoomType;

public interface RoomService {
	
	/**
	 * 增加新的房间
	 * @param room
	 * @return 成功添加返回true;添加有错误返回false
	 */
	public boolean addNewRoom(Room room);
	
	/**
	 * 删除酒店中的某个房间
	 * @return 成功删除返回true;错误删除返回false
	 */
	public boolean deleteRoom(int id);
	
	/**
	 * 按照房间号获得某个房间
	 * @param id RoomID
	 * @return
	 */
	public Room getRoomByRoomMumber(int hotelID,int roomNumber);
	
	/**
	 * 修改房间信息
	 * @param room
	 * @return
	 */
	public boolean updateRoom(Room room);
	
	/**
	 * 添加客房类型
	 * @param roomType
	 * @return
	 */
	public boolean addRoomType(RoomType roomType);
	
	/**
	 * 删除客房类型
	 * @param TypeID
	 * @return
	 */
	public boolean deleteRoomType(int TypeID);
	
	/**
	 * 允许删除客房类型
	 * @param typeID
	 * @return
	 */
	public boolean canDeleteRoomType(int typeID);
	
	/**
	 * 更新客房类型
	 * @param roomType
	 * @return
	 */
	public boolean updateRoomType(RoomType roomType);
	
	/**
	 * 显示某酒店的所有空房
	 * @param hotelid
	 * @return
	 */
	public List<Room> showEmptyRooms(int hotelid);
	
	/**
	 * 显示某酒店的所有已使用的客房
	 * @param hotelid
	 * @return
	 */
	public List<Room> showUsedRooms(int hotelid);
	
	
	
	/**
	 * 按照订单信息进行入住处理 
	 * @param order
	 * @return
	 */
	public List<Room> checkIn(int roomTypeID,int num);
	
	/**
	 * 线下客户的入住处理（即没有通过网上预订）
	 * @param roomid
	 * @return
	 */
	public boolean checkInOffline(int hotrlID,int roomNumber);
	
	/**
	 * 按照入住客户的房间号进行退房处理 
	 * @param roomID
	 * @return
	 */
	public boolean checkOut(int roomID);
	
	/**
	 * 退房处理（线下）
	 * @param roomid
	 * @return
	 */
	public boolean checkOutOffline(int hotrlID,int roomNumber);
	
	
	/**
	 * 获取房间类型
	 * @return
	 */
	public RoomType getRoomTypeByTypeID(int roomTypeID);
	
	/**
	 * 按照酒店ID获取房间类型列表
	 * @param hotelID
	 * @return
	 */
	public List<RoomType> getRoomTypeByHotelID(int hotelID);
	
	/**
	 * 添加入住记录信息
	 * @param order
	 * @param rooms
	 * @return
	 */
	public boolean addRoomCheckinItem(Order order,List<Room> rooms);
	
	/**
	 * 添加线下入住记录信息
	 * @param roomNumber
	 * @return
	 */
	public boolean addRoomCheckinItemOffline(int hotelID,int roomNumber);
	
	/**
	 * 更新RoomCheckinItem
	 * @param roomCheckItemID
	 * @return
	 */
	public boolean updateRoomCheckinItem(int roomCheckItemID);
	
	/**
	 * 获得酒店还未进行退房处理的RoomCheckItem列表
	 * @return
	 */
	public List<RoomCheckItem> getNotCheckoutRoomCheckItems(int hotelID);
	
	/**
	 * 按照酒店房间号获得还未进行checkout处理的RoomCheckItem
	 * @param itemID
	 * @return
	 */
	public RoomCheckItem getRoomCheckItemByRoomNumber(int hotelID,int roomNumber);
	
	/**
	 * 更新某类客房的信息
	 * @param roomTypeID
	 * @param price
	 * @return
	 */
	public boolean updateRoomsInfo(int roomTypeID,double price,String typeName);
	
	

}
