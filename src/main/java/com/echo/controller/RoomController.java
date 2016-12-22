package com.echo.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import com.echo.domain.po.HotelStaff;
import com.echo.domain.po.Room;
import com.echo.domain.po.RoomType;
import com.echo.domain.type.RoomStatusType;
import com.echo.service.hotelservice.HotelServiceImpl;
import com.echo.service.roomservice.RoomServiceImpl;


@SessionAttributes(value={"authHotelStaff"})
@RequestMapping("/hotelstaff") 
@Controller
public class RoomController {
	
	public static final Logger logger = Logger.getLogger(RoomController.class);
	
	@Autowired
	private HotelServiceImpl hotelServiceImpl;
	
	@Autowired
	private RoomServiceImpl roomServiceImpl;
	
	/**
	 * 该酒店客房列表的JSON信息
	 * @param hotelStaff
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getRooms")
	public List<Room> getRooms(@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		return hotelServiceImpl.getAllRooms(hotelStaff.getHotelID());
	}
	
	/**
	 * 前往客房管理页
	 */
	@RequestMapping(value="/viewRooms")
	public String viewRooms(@ModelAttribute("authHotelStaff") HotelStaff hotelStaff,Map<String,Object> map){
		List<RoomType> roomTypes = roomServiceImpl.getRoomTypeByHotelID(hotelStaff.getHotelID());
		map.put("roomTypes", roomTypes);
		return "hotelstaffview/viewRooms";
	}
	
	/**
	 * 前往修改房间类型页
	 */
	@RequestMapping(value="/roomType/{roomTypeID}",method=RequestMethod.GET)
	public String goUpdateRoomType(@PathVariable("roomTypeID") int roomTypeID,Map<String,Object> map){
		map.put("roomType", roomServiceImpl.getRoomTypeByTypeID(roomTypeID));
		return "forward:/hotelstaff/viewRooms";
	}
	
	/**
	 * 更新房间类型
	 */
	@RequestMapping(value="/roomType/{roomTypeID}",method=RequestMethod.PUT)
	public String updateRoomType(@PathVariable("roomTypeID") int roomTypeID,Map<String,Object> map,
			@RequestParam("price") double price,@RequestParam("typeName") String typeName,@RequestParam("typeDesc") String typeDesc){
		RoomType roomType = roomServiceImpl.getRoomTypeByTypeID(roomTypeID);
		if(roomType == null){
			map.put("roomTypeInfo", "<script>alert('无法获取该客房类型。');</script>");
			return "forward:/hotelstaff/viewRooms";
		}
		roomType.setPrice(price);
		roomType.setTypeDesc(typeDesc);
		roomType.setTypeName(typeName);
		if(roomServiceImpl.updateRoomType(roomType) && roomServiceImpl.updateRoomsInfo(roomTypeID, price,typeName)){
			map.put("roomTypeInfo", "<script>alert('已成功修改信息！');</script>");
			logger.info("成功更新房间信息 roomTypeID："+roomTypeID);
		}
		return "forward:/hotelstaff/viewRooms";
	}
	
	/**
	 * 删除房间类型
	 */
	@RequestMapping(value="/roomType/{roomTypeID}",method=RequestMethod.DELETE)
	public String deleteRoomType(@PathVariable("roomTypeID") int roomTypeID,Map<String,Object> map){
		if(roomServiceImpl.canDeleteRoomType(roomTypeID)){
			if(roomServiceImpl.deleteRoomType(roomTypeID)){
				map.put("roomTypeInfo", "<script>alert('删除成功');</script>");
				logger.info("成功删除房间类型 roomTypeID："+roomTypeID);
				return "forward:/hotelstaff/viewRooms";
			}
		}
		map.put("roomTypeInfo", "<script>alert('删除失败，请确定当前所有房间不是此类型。');</script>");
		return "forward:/hotelstaff/viewRooms";
	}
	
	/**
	 * 添加新房间类型
	 */
	@RequestMapping(value="/roomType/",method=RequestMethod.POST)
	public String addRoomType(RedirectAttributesModelMap map,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff,
			@RequestParam("price") double price,@RequestParam("typeName") String typeName,@RequestParam("typeDesc") String typeDesc){
		RoomType roomType = new RoomType();
		roomType.setPrice(price);
		roomType.setTypeDesc(typeDesc);
		roomType.setTypeName(typeName);
		roomType.setHotelID(hotelStaff.getHotelID());
		if(roomServiceImpl.addRoomType(roomType)){
			map.addFlashAttribute("roomTypeInfo", "<script>alert('新的客房类型添加成功。');</script>");
			logger.info("成功添加新房间类型 typeName："+typeName);
		}
		return "redirect:/hotelstaff/viewRooms";
	}
	
	/**
	 * 添加房间
	 */
	@RequestMapping(value="/room/",method=RequestMethod.POST)
	public String addRoom(RedirectAttributesModelMap map,@RequestParam("roomNum") int roomNum,@RequestParam("roomType") int roomType,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		RoomType roomtype = roomServiceImpl.getRoomTypeByTypeID(roomType);
		Room room = new Room(hotelStaff.getHotelID(), roomNum, roomtype.getTypeID(), roomtype.getTypeName(), roomtype.getPrice(), RoomStatusType.FREE);
		if(roomServiceImpl.addNewRoom(room)){
			map.addFlashAttribute("roomInfo", "<script>alert('新客房添加成功。');</script>");
			logger.info("成功添加新房间 roomtypeID："+roomtype.getTypeID()+" 房间号："+roomNum);
		}
		return "redirect:/hotelstaff/viewRooms";
	}
	
	/**
	 * 进入修改客房信息页
	 */
	@RequestMapping(value="/room/chooseRoom",method=RequestMethod.POST)
	public String goUpdateRoom(Map<String,Object> map,@RequestParam("roomNum") int roomNum,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		Room room = roomServiceImpl.getRoomByRoomMumber(hotelStaff.getHotelID(), roomNum);
		if( room == null){
			map.put("roomInfo", "<script>alert('没有该类型客房。');</script>");
		}
		else{
			map.put("searchRoom", room);
		}
		return "forward:/hotelstaff/viewRooms";
	}
	
	/**
	 * 修改客房信息
	 */
	@RequestMapping(value="/room",method=RequestMethod.PUT)
	public String updateRoom(Map<String,Object> map,@RequestParam(value="roomType") int roomType,@RequestParam(value="roomNumber") int roomNumber,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		Room room = roomServiceImpl.getRoomByRoomMumber(hotelStaff.getHotelID(), roomNumber);
		room.setTypeID(roomType);
		RoomType roomtype = roomServiceImpl.getRoomTypeByTypeID(roomType);
		room.setTypeName(roomtype.getTypeName());
		room.setPrice(roomtype.getPrice());
		if(roomServiceImpl.updateRoom(room)){
			map.put("roomInfo", "<script>alert('客房信息修改成功。');</script>");
			logger.info("客房信息修改成功：HotelID："+hotelStaff.getHotelID()+" 房间号："+roomNumber);
		}
		return "forward:/hotelstaff/viewRooms";
	}
	
	/**
	 * 删除房间
	 */
	@RequestMapping(value="/room/delete/{roomNumber}")
	public String deleteRoom(Map<String,Object> map,@PathVariable("roomNumber") int roomNumber,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		Room room = roomServiceImpl.getRoomByRoomMumber(hotelStaff.getHotelID(), roomNumber);
		if(room.getStatus() == RoomStatusType.USING){
			map.put("roomInfo", "<script>alert('该客房尚在使用中，不可删除。');</script>");
			return "forward:/hotelstaff/viewRooms";
		}
		if(roomServiceImpl.deleteRoom(room.getId())){
			map.put("roomInfo", "<script>alert('删除成功！');</script>");
			logger.info("客房成功删除：HotelID："+hotelStaff.getHotelID()+" 房间号："+roomNumber);
		}
		return "forward:/hotelstaff/viewRooms";
	}
	
	

}
