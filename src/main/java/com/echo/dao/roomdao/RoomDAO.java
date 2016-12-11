package com.echo.dao.roomdao;

import java.util.List;

import com.echo.domain.po.Room;
import com.echo.domain.po.RoomCheckItem;
import com.echo.domain.po.RoomType;


public interface RoomDAO {
	
	/**
	 * 增加房间
	 * @param room
	 * @return
	 */
	public boolean add(Room room);
	
	/**
	 * 删除房间
	 * @param room
	 * @return
	 */
	public boolean delete(int id);
	
	/**
	 * 修改房间信息
	 * @param room
	 * @return
	 */
	public boolean update(Room room);
	
	/**
	 * 按照id获得该房间的全部信息
	 * @return
	 */
	public Room get(int id);
	
	/**
	 * 按照房间号获得该房间的全部信息
	 * @return
	 */
	public Room get(int hotelID,int roomNumber);
	
	/**
	 * 获取某酒店所有房间
	 * @return
	 */
	public List<Room> getAll(int hotelID);
	
	/**
	 * 按照房间类型和状态获得所有该类房间
	 * @param hotelID 酒店ID
	 * @param typeid  房间类型编号
	 * @return
	 */
	public List<Room> getRoomsByTypeAndStatus(int typeID,byte status);
	
	/**
	 * 更新某类客房的信息
	 * @param roomTypeID
	 * @param price
	 * @return
	 */
	public boolean updateRoomsInfo(int roomTypeID,double price,String typeName);

	/**
	 * 显示某个酒店的不同状态类型的客房清单
	 * @param hotelid 酒店ID
	 * @param status  客房状态
	 * @return
	 */
	public List<Room> getRoomsByStatus(int hotelID, byte status);
	
	/**
	 * 按价格显示某酒店房间列表
	 *  @param hotelID
	 * @param floor 价格下限
	 * @param ceiling 价格上限
	 * @return
	 */
	public List<Object[]> getRoomsByPrice(int hotelID, double floor, double ceiling);
	
	/**
	 * 用于存储入住/退房信息
	 * @param roomCheckItem
	 * @return
	 */
	public boolean add(RoomCheckItem roomCheckItem);
	
	/**
	 * 用于更新入住/退房信息
	 * @param roomCheckItem
	 * @return
	 */
	public boolean update(RoomCheckItem roomCheckItem);
	
	/**
	 * 按照ID获得RoomCheckItem
	 * @param id
	 * @return
	 */
	public RoomCheckItem getCheckItemByItemID(int id) ;
	
	/**
	 * 按照Item的房间名获得RoomCheckItem
	 * @param roomNumber
	 * @return
	 */
	public List<RoomCheckItem> getCheckItemList(int hotelID,int roomNumber);
	
	/**
	 * 获得酒店还未进行退房处理的RoomCheckItem列表
	 * @param hotelID
	 * @return
	 */
	public List<RoomCheckItem> getCheckItemList(int hotelID);
	
	/**
	 * 添加新的客房类型
	 * @param hotelID 酒店ID
	 * @param typeName 类型名称
	 * @return
	 */
	public boolean add(RoomType roomType);
	
	/**
	 * 按照类型ID获取客房类型
	 * @param roomTypeID
	 * @return
	 */
	public RoomType getType(int roomTypeID);
	
	/**
	 * 删除已有的房间类型
	 * @param typeID
	 * @return
	 */
	public boolean deleteType(int typeID);
	
	/**
	 * 更新房间类型信息
	 * @param roomType
	 * @return
	 */
	public boolean update(RoomType roomType);
	
	/**
	 * 列举出某酒店是所有客房类型
	 * @param hotelID
	 * @return
	 */
	public List<RoomType> getAllType(int hotelID);
	
	

	
	
}
