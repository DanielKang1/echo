package com.echo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.echo.domain.po.CreditChangeItem;
import com.echo.domain.po.Customer;
import com.echo.domain.po.Hotel;
import com.echo.domain.po.HotelStaff;
import com.echo.domain.po.Order;
import com.echo.domain.po.Room;
import com.echo.domain.po.RoomCheckItem;
import com.echo.domain.po.RoomType;
import com.echo.domain.type.CreditOperationType;
import com.echo.domain.type.OrderStatusType;
import com.echo.domain.type.RoomStatusType;
import com.echo.domain.vo.Login;
import com.echo.service.creditservice.CreditServiceImpl;
import com.echo.service.customerservice.CustomerServiceImpl;
import com.echo.service.hotelservice.HotelServiceImpl;
import com.echo.service.hotelstaffservice.HotelStaffServiceImpl;
import com.echo.service.orderservice.OrderServiceImpl;
import com.echo.service.roomservice.RoomServiceImpl;
import com.google.code.kaptcha.Constants;

@SessionAttributes(value={"authHotelStaff"})
@RequestMapping("/hotelstaff") 
@Controller
public class HotelStaffController {
	
	@Autowired
	public HotelStaffServiceImpl hotelstaffserviceImpl;
	@Autowired
	public OrderServiceImpl orderServicceImpl;
	@Autowired
	public HotelServiceImpl hotelServiceImpl;
	@Autowired
	public RoomServiceImpl roomServiceImpl;
	@Autowired
	public CreditServiceImpl creditServiceImpl;
	@Autowired
	public CustomerServiceImpl customerServiceImpl;
	
	/**
	 * 前往登录页面
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/signin",method=RequestMethod.GET)
	public String goSignin(Map<String, Object> map){
		map.put("login", new Login()); 
		return "hotelstaffview/staffSignin";
	}

	
	/**
	 * 登录处理，使用redirect，而不是直接forward（直接转发是可以从请求中拿到用户名和密码的）
	 */
	@RequestMapping(value="/signin",method=RequestMethod.POST)
	public String signin(@Valid @ModelAttribute("login") Login login,BindingResult result, HttpSession session,Map<String,Object> map){
		if(result.getErrorCount() > 0){
			return "hotelstaffview/staffSignin";
		}
		//验证码是否正确
		String sessionCaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(!login.getCaptcha().equals(sessionCaptcha)){
			result.rejectValue("captcha", "CaptchaError");
			return "hotelstaffview/staffSignin";
		}
		HotelStaff staff = hotelstaffserviceImpl.login(login.getUservalue(), login.getPwd());
		if(staff == null){
			result.rejectValue("pwd", "LoginError");
			return "hotelstaffview/staffSignin";
		}
		hotelstaffserviceImpl.decodeHotelStaff(staff);
		map.put("authHotelStaff", staff);
		
		return "redirect:/hotelstaff/hotelManage";
	}
	
	/**
	 * 前往管理页
	 * @param hotelStaff
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/hotelManage")
	public String hotelManage(@ModelAttribute("authHotelStaff") HotelStaff hotelStaff,Map<String, Object> map){
		
		Hotel hotel = hotelServiceImpl.getHotelByID(hotelStaff.getHotelID());
		map.put("hotel",hotel);
		map.put("districts", hotelServiceImpl.getDistrictsByCity(hotel.getCity()));
		return "hotelstaffview/hotelManagement";
	}
	
 
	/**
	 * 查看订单
	 * @param hotelStaff
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/viewOrders",method=RequestMethod.GET)
	public String viewOrders(@ModelAttribute("authHotelStaff") HotelStaff hotelStaff,Map<String, Object> map){
		List<Order> orders = orderServicceImpl.getHotelOrdersByType(hotelStaff.getHotelID(), OrderStatusType.UNEXECUTED);
		map.put("orders", orders);
		map.put("ordersSize",orderServicceImpl.getOrdersSize(hotelStaff.getHotelID()));
		return "hotelstaffview/viewOrders";
	}
	
	
	/**
	 * 获取不同类型的订单
	 * @param orderStatus
	 * @param hotelStaff
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getOrders_{orderStatus}",method=RequestMethod.GET)
	public String getOrders(@PathVariable("orderStatus") byte orderStatus,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff,Map<String, Object> map){
		if(orderStatus > 5){
			map.put("orderTypeError", "<script>alert('订单状态参数错误');</script>");
			return "hotelstaffview/hotelManagement";
		}
		int hotelID = hotelStaff.getHotelID();
		List<Order> orders = orderServicceImpl.getHotelOrdersByType(hotelID, orderStatus);
		map.put("orders", orders);
		map.put("ordersSize",orderServicceImpl.getOrdersSize(hotelStaff.getHotelID()));
		return "hotelstaffview/viewOrders";
	}
	
	/**
	 * 前往入住/退房处理页
	 * @param map
	 * @param hotelStaff
	 * @return
	 */
	@RequestMapping(value="/goCheckHandle")
	public String goCheckHandle(Map<String,Object> map,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		//存放酒店还未进行退房处理的RoomCheckItem列表（也就是显示那些客房在住）
		map.put("notCheckoutRoomCheckItems", roomServiceImpl.getNotCheckoutRoomCheckItems(hotelStaff.getHotelID()));
		return "hotelstaffview/checkHandle";
	}
	
	/**
	 * 在“当前入住信息”中查看订单信息
	 * @param orderID
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getOrderInfo/{orderID}")
	public String goCheckHandle(@PathVariable("orderID") int orderID,Map<String,Object> map){
		Order order = orderServicceImpl.getOrderByID(orderID);
		if(order == null){
			map.put("orderInfoIsNull", "<script>alert('该条订单为线下订单');</script>");
			return "forward:/hotelstaff/goCheckHandle";
		}
		map.put("orderInfo", order); 
		return "forward:/hotelstaff/goCheckHandle";
	}
	
	
	/**
	 * 搜索用户的未执行订单
	 * @param phone
	 * @param hotelStaff
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/searchOrder")
	public String searchOrderByPhone(@RequestParam("phone") String phone ,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff,Map<String,Object> map){
		List<Order> result = orderServicceImpl.getOrdersByPhone(phone, hotelStaff.getHotelID());
		map.put("unexecutedOrders", result);
		return "forward:/hotelstaff/goCheckHandle";
	}
	
	/**
	 * 入住处理操作
	 * @param orderID
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/checkin_{orderID}",method=RequestMethod.GET)
	public String checkinHandle(@PathVariable("orderID") int orderID, RedirectAttributesModelMap map){
		Order order = orderServicceImpl.getOrderByID(orderID);
		
		//1.房间状态处理
		List<Room> selectBooked = roomServiceImpl.checkIn(order.getRoomType().getTypeID(),order.getBookingNum());
		//2.记录入住信息
		roomServiceImpl.addRoomCheckinItem(order, selectBooked);
		//3.订单状态处理
		orderServicceImpl.updateOrderStatus(order, OrderStatusType.EXECUTED);
		//4.添加信用处理记录
		CreditChangeItem creditChangeItem = new CreditChangeItem(order.getCustomerID(),order.getOrderID(),CreditOperationType.ORDER_EXECUTED,
				order.getHotel().getHotelID(),order.getHotel().getHotelName(),order.getTotal());
		creditServiceImpl.generateItem(creditChangeItem);
		//5.修改用户信用记录值
		Customer targetCustomer = customerServiceImpl.getBasicInfo(order.getCustomerID());
		customerServiceImpl.modifyCredit(targetCustomer, order.getTotal());
		
		StringBuilder sb = new StringBuilder("入住的房间号是：");
		for(Room room : selectBooked){
			sb.append(room.getRoomNumber() +" ");
		}
		System.out.println(sb.toString());
		map.addFlashAttribute("roomNumbers", "<script>alert('"+sb.toString()+"');</script>");
		return "redirect:/hotelstaff/goCheckHandle";
	}
	
	/**
	 * 线下入住处理
	 * @param roomNumber
	 * @param hotelStaff
	 * @return
	 */
	@RequestMapping(value="/checkinOffline",method=RequestMethod.POST)
	public String checkinOffline(@RequestParam("roomNumber") int roomNumber,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff,Map<String,Object> map){
		RoomCheckItem  item = roomServiceImpl.getRoomCheckItemByRoomNumber(hotelStaff.getHotelID(), roomNumber);
		if(item != null){
			map.put("checkOfflineInfo", "<script>alert('入住处理失败，当前房间已入住');</script>");
			return "forward:/hotelstaff/goCheckHandle";
		}
		if(roomServiceImpl.checkInOffline(hotelStaff.getHotelID(), roomNumber) == false){
			map.put("checkOfflineInfo", "<script>alert('入住处理失败，请填写正确的房间号');</script>");
			return "forward:/hotelstaff/goCheckHandle";
		}
		if(roomServiceImpl.addRoomCheckinItemOffline(hotelStaff.getHotelID(),roomNumber)){
			map.put("checkOfflineInfo", "<script>alert('线下入住处理成功！');</script>");
		}
		return "forward:/hotelstaff/goCheckHandle";
	}
	
	/**
	 * 退房处理
	 * @param roomCheckItemID
	 * @param roomNumber
	 * @param map
	 * @param hotelStaff
	 * @return
	 */
	@RequestMapping(value="/checkout_{id}&roomNumber_{roomNumber}")
	public String checkoutHandle(@PathVariable("id") int roomCheckItemID,@PathVariable("roomNumber") int roomNumber,Map<String,Object> map,
			@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
			//1.房间状态
			if(roomServiceImpl.checkOut(roomCheckItemID)){
				//2.更新RoomCheckItem
				if(roomServiceImpl.updateRoomCheckinItem(roomCheckItemID)){
					map.put("checkoutSuccess","<script>alert('退房处理成功！');</script>");
				}
			}
			return "forward:/hotelstaff/goCheckHandle";
	}
	

	/**
	 * 线下退房处理
	 * @param roomNumber
	 * @param hotelStaff
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/checkoutOffline",method=RequestMethod.POST)
	public String checkoutOffline(@RequestParam("roomNumber") int roomNumber,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff,Map<String,Object> map){
		RoomCheckItem  item = roomServiceImpl.getRoomCheckItemByRoomNumber(hotelStaff.getHotelID(), roomNumber);
		if(item == null){
			map.put("checkOfflineInfo", "<script>alert('退房处理失败，该房间并未入住');</script>");
			return "forward:/hotelstaff/goCheckHandle";
		}
		if(roomServiceImpl.checkOutOffline(hotelStaff.getHotelID(), roomNumber) == false){
			map.put("checkOfflineInfo", "<script>alert('退房处理失败，请填写正确的房间号');</script>");
			return "forward:/hotelstaff/goCheckHandle";
		}
		
		if(roomServiceImpl.updateRoomCheckinItem(item.getId())){
			map.put("checkOfflineInfo", "<script>alert('线下退房处理成功！');</script>");
		}
		
		return "forward:/hotelstaff/goCheckHandle";
	}
	
	
	/**
	 * 获取使用中的客房以进行退房处理
	 * @param roomNumber
	 * @param map
	 * @param hotelStaff
	 * @return
	 */
	@RequestMapping(value="/getUsingRoom")
	public String getUsingRoom(@RequestParam("roomNumber") String roomNumber,Map<String,Object> map,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		int roomNumber_ = 0;
		try {
			roomNumber_ = Integer.parseInt(roomNumber);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			map.put("getRoomError", "房间号非法！");
			return "forward:/hotelstaff/goCheckHandle";
		}
		
		RoomCheckItem roomcheckItem = roomServiceImpl.getRoomCheckItemByRoomNumber(hotelStaff.getHotelID(), roomNumber_);
		if(roomcheckItem == null ){
			map.put("getRoomError", "该房间不存在或者不是已入住状态！");
			return "forward:/hotelstaff/goCheckHandle";
		}
		Order order = orderServicceImpl.getOrderByID(roomcheckItem.getOrderID());
		map.put("roomcheckItemSearchResult", roomcheckItem);
		map.put("orderSearchResult", order);
		return "forward:/hotelstaff/goCheckHandle";
	}
	
	
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
	
	
	@RequestMapping(value="/viewRooms")
	public String viewRooms(@ModelAttribute("authHotelStaff") HotelStaff hotelStaff,Map<String,Object> map){
		List<RoomType> roomTypes = roomServiceImpl.getRoomTypeByHotelID(hotelStaff.getHotelID());
		map.put("roomTypes", roomTypes);
		return "hotelstaffview/viewRooms";
	}
	
	
	@RequestMapping(value="/roomType/{roomTypeID}",method=RequestMethod.GET)
	public String goUpdateRoomType(@PathVariable("roomTypeID") int roomTypeID,Map<String,Object> map){
		map.put("roomType", roomServiceImpl.getRoomTypeByTypeID(roomTypeID));
		return "forward:/hotelstaff/viewRooms";
	}
	
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
		}
		return "forward:/hotelstaff/viewRooms";
	}
	
	@RequestMapping(value="/roomType/{roomTypeID}",method=RequestMethod.DELETE)
	public String deleteRoomType(@PathVariable("roomTypeID") int roomTypeID,Map<String,Object> map){
		if(roomServiceImpl.canDeleteRoomType(roomTypeID)){
			if(roomServiceImpl.deleteRoomType(roomTypeID)){
				map.put("roomTypeInfo", "<script>alert('删除成功');</script>");
				return "forward:/hotelstaff/viewRooms";
			}
		}
		map.put("roomTypeInfo", "<script>alert('删除失败，请确定当前所有房间不是此类型。');</script>");
		return "forward:/hotelstaff/viewRooms";
	}
	
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
		}
		return "redirect:/hotelstaff/viewRooms";
	}
	
	@RequestMapping(value="/room/",method=RequestMethod.POST)
	public String addRoom(RedirectAttributesModelMap map,@RequestParam("roomNum") int roomNum,@RequestParam("roomType") int roomType,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		RoomType roomtype = roomServiceImpl.getRoomTypeByTypeID(roomType);
		Room room = new Room(hotelStaff.getHotelID(), roomNum, roomtype.getTypeID(), roomtype.getTypeName(), roomtype.getPrice(), RoomStatusType.FREE);
		if(roomServiceImpl.addNewRoom(room)){
			map.addFlashAttribute("roomInfo", "<script>alert('新客房添加成功。');</script>");
		}
		return "redirect:/hotelstaff/viewRooms";
	}
	
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
	
	@RequestMapping(value="/room",method=RequestMethod.PUT)
	public String updateRoom(Map<String,Object> map,@RequestParam(value="roomType") int roomType,@RequestParam(value="roomNumber") int roomNumber,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		System.out.println(roomNumber);
		System.out.println(roomType);
		Room room = roomServiceImpl.getRoomByRoomMumber(hotelStaff.getHotelID(), roomNumber);
		room.setTypeID(roomType);
		RoomType roomtype = roomServiceImpl.getRoomTypeByTypeID(roomType);
		room.setTypeName(roomtype.getTypeName());
		room.setPrice(roomtype.getPrice());
		if(roomServiceImpl.updateRoom(room)){
			map.put("roomInfo", "<script>alert('客房信息修改成功。');</script>");
		}
		return "forward:/hotelstaff/viewRooms";
	}
	
	@RequestMapping(value="/room/delete/{roomNumber}")
	public String deleteRoom(Map<String,Object> map,@PathVariable("roomNumber") int roomNumber,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		Room room = roomServiceImpl.getRoomByRoomMumber(hotelStaff.getHotelID(), roomNumber);
		if(room.getStatus() == RoomStatusType.USING){
			map.put("roomInfo", "<script>alert('该客房尚在使用中，不可删除。');</script>");
			return "forward:/hotelstaff/viewRooms";
		}
		if(roomServiceImpl.deleteRoom(room.getId())){
			map.put("roomInfo", "<script>alert('删除成功！');</script>");
		}
		return "forward:/hotelstaff/viewRooms";
	}
	
	
	
	/**
	 * 修改酒店信息
	 */
	@RequestMapping(value="/updateInfo",method=RequestMethod.POST)
	public String updateInfo(@RequestParam("district") String district,@RequestParam("address") String address,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff,
			@RequestParam("facility") String facility,@RequestParam("briefIntro") String briefIntro,Map<String, Object> map){
		Hotel hotel = hotelServiceImpl.getHotelByID(hotelStaff.getHotelID());
		hotel.setAddress(address);
		hotel.setBriefIntro(briefIntro);
		hotel.setFacility(facility);
		hotel.setDistrict(district);
		if(hotelServiceImpl.updateBasicInfo(hotel)){
			map.put("hotelInfo", "<script>alert('更新成功！');</script>");
		}else{
			map.put("hotelInfo", "<script>alert('更新失败！');</script>");
		}
		return "forward:/hotelstaff/hotelManage";
	}
	
	@RequestMapping(value="/modifyStarLevel",method=RequestMethod.POST)
	public String updateStarLevel(@RequestParam("starLevel") byte starLevel,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff,Map<String, Object> map){
		Hotel hotel = hotelServiceImpl.getHotelByID(hotelStaff.getHotelID());
		hotel.setStarLevel(starLevel);
		if(hotelServiceImpl.updateBasicInfo(hotel)){
			map.put("hotelInfo", "<script>alert('更新成功！');</script>");
		}else{
			map.put("hotelInfo", "<script>alert('更新失败！');</script>");
		}
		return "forward:/hotelstaff/hotelManage";
	}
	
	
}
