package com.echo.controller;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

import com.echo.domain.po.CreditChangeItem;
import com.echo.domain.po.Customer;
import com.echo.domain.po.Hotel;
import com.echo.domain.po.Order;
import com.echo.domain.po.RoomType;
import com.echo.domain.type.CreditOperationType;
import com.echo.domain.type.OrderStatusType;
import com.echo.service.creditservice.CreditServiceImpl;
import com.echo.service.customerservice.CustomerServiceImpl;
import com.echo.service.hotelpromotionservice.HotelPriceHandleContext;
import com.echo.service.hotelpromotionservice.HotelPromotionParameters;
import com.echo.service.hotelservice.HotelServiceImpl;
import com.echo.service.orderservice.OrderServiceImpl;
import com.echo.service.roomservice.RoomServiceImpl;
import com.echo.service.webpromotionservice.WebPromotionHandleContext;
import com.echo.service.webpromotionservice.WebPromotionParameters;
import com.echo.utils.DateUtils;

@SessionAttributes(value={"authCustomer"})
@RequestMapping("/customer") 
@Controller
public class CustomerOrderController {
	
	public static final Logger logger = Logger.getLogger(CustomerOrderController.class);
	
	@Autowired
	private HotelServiceImpl hotelServiceImpl;
	@Autowired
	private RoomServiceImpl roomServiceImpl;
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	@Autowired
	private CreditServiceImpl creditServiceImpl;
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@Autowired
	private HotelPriceHandleContext hotelPriceHandleContext;
	@Autowired
	private WebPromotionHandleContext webPromotionHandleContext;
	
	/**
	 * 前往生成订单页
	 */
	@RequestMapping(value="/order/hotel_{hotelID}&roomType_{roomTypeID}",method=RequestMethod.GET)
	public String goGenerateOrder(@PathVariable("hotelID") Integer hotelID,@PathVariable("roomTypeID") Integer roomTypeID,Map<String,Object> map){
		Hotel hotel = hotelServiceImpl.getHotelByID(hotelID);
		RoomType roomType = roomServiceImpl.getRoomTypeByTypeID(roomTypeID);
		map.put("hotel", hotel);
		map.put("roomType", roomType);
		return "generateOrder";
	}	
	
	
	/**
	 * 检查选择的预订日期段是否还有房间可以预订（AJax）
	 * @param checkindate  选择的入住时间
	 * @param checkoutdate  选择的退房时间
	 * @param bookingNum    选择的预订数量
	 * @param roomTypeID   房间型号
	 */
	@RequestMapping(value="/order/checkAllowBookOrNot")
	public void checkAllowBookOrNot(@RequestParam(value="checkindate",required=false) String checkindate,@RequestParam(value="checkoutdate",required=false) String checkoutdate,
			@RequestParam(value="bookingNum",required=false) String bookingNum,@RequestParam(value="roomTypeID",required=false) String roomTypeID,
			PrintWriter out,HttpSession session){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date checkin = null;
		Date checkout = null;
		try {
			checkin = sdf.parse(checkindate);
			checkout = sdf.parse(checkoutdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(hotelServiceImpl.allowBookingOrNot(checkin, checkout, Integer.parseInt(roomTypeID), Integer.parseInt(bookingNum))){
			out.print("true");
		}else{
			out.println("false");
		}
			
	}
	
	
	/**
	 * 生成订单操作
	 * @throws  
	 */
	@RequestMapping(value="/order/submitOrder&hotel_{hotelID}&roomType_{roomTypeID}",method=RequestMethod.POST)
	public String generateOrder(@PathVariable("hotelID") Integer hotelID,@PathVariable("roomTypeID") Integer roomTypeID,@ModelAttribute("authCustomer") Customer customer,
			RedirectAttributesModelMap map,@RequestParam("checkinDate") String checkinDate,@RequestParam("checkoutDate") String checkoutDate,@RequestParam("bookingNum") Integer bookingNum,
			@RequestParam("peopleNum") Integer peopleNum,@RequestParam("roomPrice") Double roomPrice, @RequestParam("hasChild") byte hasChild,@RequestParam("captcha") String captcha,
			@RequestParam("reservedName") String reservedName,@RequestParam("reservedPhone") String reservedPhone) {
		
		//生成订单前会检查信用值，其操作在GenerateOrderInterceptor里
		//获得入住及退房日期
		Date checkindate = DateUtils.getDate(checkinDate);
		Date checkoutdate = DateUtils.getDate(checkoutDate);
		Date latestDate = null;
		
		//提交订单前务必先验证住的日子的客房是否已被预订满
		if(!hotelServiceImpl.allowBookingOrNot(checkindate, checkoutdate, roomTypeID, bookingNum)){
				map.addFlashAttribute("OrderErrorInfo", "<script>alert('预订失败。该类型客房在您选择的时间段内已经被预订满！您可以调整预订数量或入住时间。');</script>");
				return "redirect:/customer/order/hotel_"+hotelID+"&roomType_"+roomTypeID;
		}
		
		//将用户输入的日期转化成真正的入住/退房/最迟日期 （惯例：宾馆的入住时间为14：00，离店时间为正午12：00）
		latestDate = DateUtils.getCorrectiveLatestDate(checkindate);   //最晚入住时间（入住那天的24点）
		checkindate = DateUtils.getCorrectiveCheckinDate(checkindate);   //入住时间
		checkoutdate = DateUtils.getCorrectiveCheckoutDate(checkoutdate); //退房时间
		
		Order order = new Order(OrderStatusType.UNEXECUTED, hotelServiceImpl.getHotelByID(hotelID), customer.getCustomer_id(), reservedName, reservedPhone,
				new Date(), checkindate, checkoutdate, latestDate, roomServiceImpl.getRoomTypeByTypeID(roomTypeID), bookingNum, peopleNum, hasChild);
		//-----------------------------------价格计算--------------------------------------------------
		double originalPrice = DateUtils.getDaysDiff(checkoutdate, checkindate)*roomPrice*bookingNum ; //初始价格
		//酒店促销
		HotelPromotionParameters hotelParas = new HotelPromotionParameters(hotelID, originalPrice, customer.getBirthday(), bookingNum,customer.getCustomer_id());
		hotelPriceHandleContext.setHotelPromotionParameters(hotelParas);
		double hotel_result = hotelPriceHandleContext.getResult(); //酒店促销后的价格
		//网站营销
		WebPromotionParameters webParas =  new WebPromotionParameters(customer.getGrade(), hotelServiceImpl.getHotelByID(hotelID).getDistrict());
		webPromotionHandleContext.setWebPromotionParameters(webParas);
		double web_result = webPromotionHandleContext.getFinalDiscount(); //网站营销的折扣
		//最终结果
		String priceRes = String.format("%.1f",hotel_result * web_result);
		order.setTotal(Double.parseDouble(priceRes));
		
		if(orderServiceImpl.generateOrder(order)){
			logger.info("新添加订单  用户ID："+order.getCustomerID()+" 酒店ID："+order.getHotel().getHotelID()+" 预留手机号："+order.getReservedPhone() );
			//使用RedirectAttributesModelMap可以在redirect时放Attribute，可防止重复提交。
			map.addFlashAttribute("orderInfo", "<script>alert('您已成功预订！到店支付房费"+order.getTotal()+"！');</script>");
			return "redirect:/all";
		}else{
			logger.error("订单生成异常  用户ID："+order.getCustomerID()+" 酒店ID："+order.getHotel().getHotelID());
			map.addFlashAttribute("orderInfo", "<script>alert('预订失败...请重新尝试...');</script>");
			return "redirect:/all";
		}
	}
	
	
	/**
	 * 前往个人订单管理页
	 */
	@RequestMapping(value="goViewOrders",method=RequestMethod.GET)
	public String goViewOrders(@ModelAttribute("authCustomer") Customer customer,Map<String,Object> map){
		List<Order> cusOrders = orderServiceImpl.getCustomerOrders(customer.getCustomer_id());
		map.put("orders", cusOrders);
		Map<Hotel,Integer> staResult = orderServiceImpl.getOrderTimesByHotel(customer.getCustomer_id()); //订购次数的统计结果
		map.put("staResult", staResult);
		return "customerview/order";
	}
	
	/**
	 * 个人订单页的分类操作
	 */
	@RequestMapping(value="viewOrdersByType/{typeID}",method=RequestMethod.GET)
	public String viewOrdersByType(@ModelAttribute("authCustomer") Customer customer,Map<String,Object> map,@PathVariable("typeID") byte typeID){
		List<Order> ordersByType = orderServiceImpl.getCustomerOrdersByType(customer.getCustomer_id(), typeID);
		map.put("ordersByType", ordersByType);
		return "forward:/customer/goViewOrders";
	}
	
	/**
	 * 取消订单
	 */
	@RequestMapping(value="cancelorder/{orderID}",method=RequestMethod.GET)
	public String cancelOrder(@PathVariable("orderID") int orderID,Map<String,Object> map){
		Order order = orderServiceImpl.getOrderByID(orderID);
		if(order != null){
			//撤销时间离最晚执行时间小于6个小时，则扣除订单金额的一半信用值
			if(DateUtils.apartOver6hours(order.getLatestDate())){
				double oldResult = customerServiceImpl.getBasicInfo(order.getCustomerID()).getCredit() + (-0.5) * order.getTotal(); 
				CreditChangeItem item = new CreditChangeItem(order.getCustomerID(), order.getOrderID(), CreditOperationType.CANCEL_ORDER_LATE, order.getHotel().getHotelID(), 
						order.getHotel().getHotelName(),oldResult ,(-0.5) * order.getTotal());
				creditServiceImpl.generateItem(item);
				customerServiceImpl.modifyCredit(customerServiceImpl.getBasicInfo(order.getCustomerID()), (-0.5) * order.getTotal());
			}
			if(orderServiceImpl.updateOrderStatus(order, OrderStatusType.CANCELLED)){
				logger.info("取消订单  订单号："+order.getOrderID()+" 用户ID："+order.getCustomerID() );
				map.put("Info","<script type='text/javascript'>alert('取消订单成功！') </script>" );
			}else{
				logger.error("取消订单失败  订单号："+order.getOrderID()+" 用户ID："+order.getCustomerID() );
				map.put("Info","<script type='text/javascript'>alert('取消订单失败.') </script>" );
			}
		}
		return "forward:/customer/goViewOrders";
	}
	
	/**
	 * 查看订单详情
	 */
	@RequestMapping(value="order/detail/{orderID}",method=RequestMethod.GET)
	public String viewDetail(@PathVariable("orderID") int orderID,Map<String,Object> map){
		Order order = orderServiceImpl.getOrderByID(orderID);
		map.put("orderInfo", order);
		return "customerview/orderDetail";
	}
	
	

}
