package com.echo.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.echo.domain.po.Customer;
import com.echo.domain.po.HotelStaff;
import com.echo.domain.po.Order;
import com.echo.domain.po.RoomCheckItem;
import com.echo.service.customerservice.CustomerServiceImpl;
import com.echo.service.hotelstaffservice.CheckService;
import com.echo.service.orderservice.OrderServiceImpl;
import com.echo.service.roomservice.RoomServiceImpl;

/**
 * 酒店工作人员进行入住及退房处理
 */
@SessionAttributes(value={"authHotelStaff"})
@RequestMapping("/hotelstaff") 
@Controller
public class CheckController {
	
	public static final Logger logger = Logger.getLogger(CheckController.class);
	
	@Autowired
	private RoomServiceImpl roomServiceImpl;
	@Autowired
	private CheckService checkService;
	@Autowired
	private OrderServiceImpl orderServicceImpl;
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
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
		Customer customer = customerServiceImpl.getBasicInfo(order.getCustomerID());
		if(order != null){
			String result =  checkService.checkin(order,customer.getCredit()+order.getTotal());
			map.addFlashAttribute("roomNumbers", "<script>alert('"+result+"');</script>");
			logger.info("酒店入住处理  HotelID："+order.getHotel().getHotelID()+"  订单ID："+order.getOrderID()+"----"+result);
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
	 */
	@RequestMapping(value="/checkout_{id}&roomNumber_{roomNumber}")
	public String checkoutHandle(@PathVariable("id") int roomCheckItemID,Map<String,Object> map,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
			if(checkService.checkout(roomCheckItemID)){
				map.put("checkoutSuccess","<script>alert('退房处理成功！');</script>");
				logger.info("退房处理成功  hotelID："+hotelStaff.getHotelID()+"  roomCheckItemID："+roomCheckItemID);
			}
			return "forward:/hotelstaff/goCheckHandle";
	}
	

	/**
	 * 线下退房处理
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
	 * 获取使用中的客房（以进行退房处理）
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
	
	

}
