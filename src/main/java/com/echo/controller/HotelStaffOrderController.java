package com.echo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.echo.domain.po.HotelStaff;
import com.echo.domain.po.Order;
import com.echo.domain.type.OrderStatusType;
import com.echo.service.orderservice.OrderServiceImpl;


@SessionAttributes(value={"authHotelStaff"})
@RequestMapping("/hotelstaff") 
@Controller
public class HotelStaffOrderController {
	
	@Autowired
	private OrderServiceImpl orderServicceImpl;
	
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
	 * 搜索用户的订单(未执行/异常)
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
	

}
