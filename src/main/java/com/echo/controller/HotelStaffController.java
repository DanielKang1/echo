package com.echo.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
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
import com.echo.domain.po.HotelPromotionItem;
import com.echo.domain.po.HotelStaff;
import com.echo.domain.po.Order;
import com.echo.domain.po.PromotionDate;
import com.echo.domain.po.Room;
import com.echo.domain.po.RoomCheckItem;
import com.echo.domain.po.RoomType;
import com.echo.domain.type.CreditOperationType;
import com.echo.domain.type.OrderStatusType;
import com.echo.domain.type.RoomStatusType;
import com.echo.domain.vo.Login;
import com.echo.service.creditservice.CreditServiceImpl;
import com.echo.service.customerservice.CustomerServiceImpl;
import com.echo.service.hotelpromotionservice.HotelPromotionServiceImpl;
import com.echo.service.hotelservice.HotelServiceImpl;
import com.echo.service.hotelstaffservice.HotelStaffServiceImpl;
import com.echo.service.orderservice.OrderServiceImpl;
import com.echo.service.roomservice.RoomServiceImpl;
import com.google.code.kaptcha.Constants;

@SessionAttributes(value={"authHotelStaff"})
@RequestMapping("/hotelstaff") 
@Controller
public class HotelStaffController {
	
	public static final Logger logger = Logger.getLogger(HotelStaffController.class);
	
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
	@Autowired
	public HotelPromotionServiceImpl hotelPromotionServiceImpl;
	
	
	//------------------------------------------------------------登录/退出-------------------------------------------------------------
	
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
		logger.info("酒店管理人员登录  HotelID："+staff.getHotelID()+" StaffName："+staff.getStaffName());
		return "redirect:/hotelstaff/hotelManage";
	}
	
	
	@RequestMapping(value="/signout")
	public String signout(Map<String,Object> map){
		/*
		 * session中删除操作已放到SignoutInterceptors拦截器中完成
		 */
		return "redirect:/hotelstaff/signin";
	}
	
	
	//------------------------------------------------------------酒店基础信息管理-------------------------------------------------------------
	
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
	 * 修改酒店信息
	 */
	@RequestMapping(value="/updateInfo",method=RequestMethod.POST)
	public String updateInfo(@RequestParam("district") String district,@RequestParam("address") String address,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff,
			@RequestParam("facility") String facility,@RequestParam("briefIntro") String briefIntro,Map<String, Object> map){
		Hotel hotel = hotelServiceImpl.getHotelByID(hotelStaff.getHotelID());
		if(hotel != null){
			hotel.setAddress(address);
			hotel.setBriefIntro(briefIntro);
			hotel.setFacility(facility);
			hotel.setDistrict(district);
		}
		if(hotelServiceImpl.updateBasicInfo(hotel)){
			map.put("hotelInfo", "<script>alert('更新成功！');</script>");
			logger.info("酒店信息更新成功 HotelID："+hotelStaff.getHotelID());
		}else{
			map.put("hotelInfo", "<script>alert('更新失败！');</script>");
			logger.error("酒店信息更新失败 HotelID："+hotelStaff.getHotelID());
		}
		return "forward:/hotelstaff/hotelManage";
	}
	
	/**
	 * 更新星级
	 * @param starLevel
	 * @param hotelStaff
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/modifyStarLevel",method=RequestMethod.POST)
	public String updateStarLevel(@RequestParam("starLevel") byte starLevel,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff,Map<String, Object> map){
		Hotel hotel = hotelServiceImpl.getHotelByID(hotelStaff.getHotelID());
		hotel.setStarLevel(starLevel);
		if(hotelServiceImpl.updateBasicInfo(hotel)){
			map.put("hotelInfo", "<script>alert('更新成功！');</script>");
			logger.info("酒店星级更新成功 HotelID："+hotelStaff.getHotelID());
		}else{
			map.put("hotelInfo", "<script>alert('更新失败！');</script>");
			logger.error("酒店星级更新失败 HotelID："+hotelStaff.getHotelID());
		}
		return "forward:/hotelstaff/hotelManage";
	}
 
	
	//------------------------------------------------------------酒店订单管理-------------------------------------------------------------
	
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
		if(orderStatus == OrderStatusType.EXECUTED){
			List<Order> orders2 = orderServicceImpl.getHotelOrdersByType(hotelID, OrderStatusType.EVALUATED);
			orders.addAll(orders2);
		}

		map.put("orders", orders);
		map.put("ordersSize",orderServicceImpl.getOrdersSize(hotelStaff.getHotelID()));
		return "hotelstaffview/viewOrders";
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
	
	
	//-----------------------------------------------------------入住/退房处理-------------------------------------------------------------
	
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
	 * 入住处理操作
	 * @param orderID
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/checkin_{orderID}",method=RequestMethod.GET)
	public String checkinHandle(@PathVariable("orderID") int orderID, RedirectAttributesModelMap map){
		Order order = orderServicceImpl.getOrderByID(orderID);
		if(order != null){
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
			map.addFlashAttribute("roomNumbers", "<script>alert('"+sb.toString()+"');</script>");
			logger.info("酒店入住处理  HotelID："+order.getHotel().getHotelID()+"  订单ID："+order.getOrderID()+"----"+sb.toString());
		}
		
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
				logger.info("退房处理成功  hotelID："+hotelStaff.getHotelID()+"  roomCheckItemID："+roomCheckItemID);
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
	
	
	//-----------------------------------------------------------客房管理-------------------------------------------------------------
	
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
	
	
	//-----------------------------------------------------------酒店营销策略-------------------------------------------------------------
	
	/**
	 * 前往营销策略页
	 */
	@RequestMapping(value="/promotions",method=RequestMethod.GET)
	public String goPromotionPage(@ModelAttribute("authHotelStaff") HotelStaff hotelStaff,Map<String,Object> map){
		//可以在添加酒店时加入
//		if(hotelPromotionServiceImpl.getHotelPromotionItem(hotelStaff.getHotelID()) == null){
//			HotelPromotionItem item = new HotelPromotionItem(hotelStaff.getHotelID(), 1, false, 10, 1, false, 1, false);
//			hotelPromotionServiceImpl.addHotelPromotionItem(item);
//		}
		HotelPromotionItem hotelPromotionItem = hotelPromotionServiceImpl.getHotelPromotionItem(hotelStaff.getHotelID());
		List<PromotionDate> prodates = hotelPromotionServiceImpl.getHotelPromotionDateList(hotelStaff.getHotelID());
		map.put("hotelPromotionItem", hotelPromotionItem);
		map.put("prodates", prodates);
		return "hotelstaffview/promotions";
	}
	
	/**
	 * 开启/关闭 生日折扣
	 * @param checkbox1
	 * @param hotelStaff
	 * @return
	 */
	@RequestMapping(value="/bir_switch_update",method=RequestMethod.POST)
	public String birthdaySwitchUpdate(@RequestParam(value="checkbox1",required=false) Object checkbox1,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		HotelPromotionItem hotelPromotionItem = hotelPromotionServiceImpl.getHotelPromotionItem(hotelStaff.getHotelID());
		if(null == checkbox1){
			hotelPromotionItem.setBirthdaySwitch(false);
			hotelPromotionServiceImpl.updateHotelPromotionItem(hotelPromotionItem);
		}else{
			hotelPromotionItem.setBirthdaySwitch(true);
			hotelPromotionServiceImpl.updateHotelPromotionItem(hotelPromotionItem);
		}
		return "redirect:/hotelstaff/promotions";
	}
	
	/**
	 * 更新生日折扣信息
	 * @param discount
	 * @param hotelStaff
	 * @return
	 */
	@RequestMapping(value="/bir_update",method=RequestMethod.POST)
	public String birthdayUpdate(@RequestParam(value="discount") float discount,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		HotelPromotionItem hotelPromotionItem = hotelPromotionServiceImpl.getHotelPromotionItem(hotelStaff.getHotelID());
		hotelPromotionItem.setBirthdayDiscount(discount);
		hotelPromotionServiceImpl.updateHotelPromotionItem(hotelPromotionItem);
		return "redirect:/hotelstaff/promotions";
	}
	
	/**
	 * 开启/关闭 预订特定数量折扣
	 * @param checkbox2
	 * @param hotelStaff
	 * @return
	 */
	@RequestMapping(value="/num_switch_update",method=RequestMethod.POST)
	public String bookingNumSwitchUpdate(@RequestParam(value="checkbox2",required=false) Object checkbox2,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		HotelPromotionItem hotelPromotionItem = hotelPromotionServiceImpl.getHotelPromotionItem(hotelStaff.getHotelID());
		if(null == checkbox2){
			hotelPromotionItem.setBookingDiscountSwitch(false);
			hotelPromotionServiceImpl.updateHotelPromotionItem(hotelPromotionItem);
		}else{
			hotelPromotionItem.setBookingDiscountSwitch(true);
			hotelPromotionServiceImpl.updateHotelPromotionItem(hotelPromotionItem);
		}
		return "redirect:/hotelstaff/promotions";
	}
	/**
	 * 更新预订特定数量折扣信息 
	 * @param discount
	 * @param number
	 * @param hotelStaff
	 * @return
	 */
	@RequestMapping(value="/num_update",method=RequestMethod.POST)
	public String bookingNumUpdate(@RequestParam(value="discount") float discount,@RequestParam(value="number") int number,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		HotelPromotionItem hotelPromotionItem = hotelPromotionServiceImpl.getHotelPromotionItem(hotelStaff.getHotelID());
		hotelPromotionItem.setBookingDiscount(discount);
		hotelPromotionItem.setBookingMeasure(number);
		hotelPromotionServiceImpl.updateHotelPromotionItem(hotelPromotionItem);
		return "redirect:/hotelstaff/promotions";
	}
	
	/**
	 * 开启/关闭  合作企业折扣
	 * @param checkbox3
	 * @param hotelStaff
	 * @return
	 */
	@RequestMapping(value="/coo_switch_update",method=RequestMethod.POST)
	public String cooUserSwitchUpdate(@RequestParam(value="checkbox3",required=false) Object checkbox3,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		HotelPromotionItem hotelPromotionItem = hotelPromotionServiceImpl.getHotelPromotionItem(hotelStaff.getHotelID());
		if(null == checkbox3){
			hotelPromotionItem.setCooperativeEnterpriseSwitch(false);
			hotelPromotionServiceImpl.updateHotelPromotionItem(hotelPromotionItem);
		}else{
			hotelPromotionItem.setCooperativeEnterpriseSwitch(true);
			hotelPromotionServiceImpl.updateHotelPromotionItem(hotelPromotionItem);
		}
		return "redirect:/hotelstaff/promotions";
	}
	
	/**
	 * 更新合作企业折扣
	 * @param discount
	 * @param hotelStaff
	 * @return
	 */
	@RequestMapping(value="/coo_update",method=RequestMethod.POST)
	public String cooUpdate(@RequestParam(value="discount") float discount,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		HotelPromotionItem hotelPromotionItem = hotelPromotionServiceImpl.getHotelPromotionItem(hotelStaff.getHotelID());
		hotelPromotionItem.setCooperativeEnterpriseDiscount(discount);
		hotelPromotionServiceImpl.updateHotelPromotionItem(hotelPromotionItem);
		return "redirect:/hotelstaff/promotions";
	}
	
	/**
	 * 添加特定日期段促销数据
	 * @param hotelStaff
	 * @param start 开始日期
	 * @param end  结束日期
	 * @param discount 折扣率
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/promotiondate",method=RequestMethod.POST)
	public String addPromotionDate(@ModelAttribute("authHotelStaff") HotelStaff hotelStaff,@RequestParam(value="start") String start,@RequestParam(value="end") String end,@RequestParam(value="discount") float discount) throws ParseException{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date start_ = dateFormat.parse(start);
		Date end_ = dateFormat.parse(end);
		PromotionDate promotionDate = new PromotionDate(hotelStaff.getHotelID(), start_, end_, discount);
		hotelPromotionServiceImpl.addPromotionDateItem(promotionDate);
		return "redirect:/hotelstaff/promotions";
	}
	
	/**
	 * 删除特定日期段促销数据
	 * @param hotelStaff
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/promotiondate/delete/{id}",method=RequestMethod.GET)
	public String deletePromotionDate(@ModelAttribute("authHotelStaff") HotelStaff hotelStaff,@PathVariable("id") int id){
		if(hotelStaff != null){
			List<PromotionDate> prodates = hotelPromotionServiceImpl.getHotelPromotionDateList(hotelStaff.getHotelID());
			boolean flag = false;
			for(PromotionDate pdate : prodates){
				if(pdate.getId() == id){
					flag = true;
					break;
				}
			}
			if(flag){
				hotelPromotionServiceImpl.deletePromotionDateItem(id);
			}
		}
		
		return "redirect:/hotelstaff/promotions";
	}
	
}
