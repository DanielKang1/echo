package com.echo.controller;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.echo.domain.po.Customer;
import com.echo.domain.po.Hotel;
import com.echo.domain.po.Order;
import com.echo.domain.po.RoomType;
import com.echo.domain.type.OrderStatusType;
import com.echo.domain.vo.Login;
import com.echo.service.customerservice.CustomerServiceImpl;
import com.echo.service.hotelservice.HotelServiceImpl;
import com.echo.service.orderservice.OrderServiceImpl;
import com.echo.service.roomservice.RoomServiceImpl;
import com.echo.utils.DateUtils;
import com.echo.utils.EncodeUtils;
import com.google.code.kaptcha.Constants;

@SessionAttributes(value={"authCustomer"})
@RequestMapping("/customer") 
@Controller
public class CustomerController {
	
	public static final Logger logger = Logger.getLogger(CustomerController.class);
	
	@Autowired
	public CustomerServiceImpl customerServiceImpl;
	
	@Autowired
	public HotelServiceImpl hotelServiceImpl;
	
	@Autowired
	public RoomServiceImpl roomServiceImpl;
	
	@Autowired
	public OrderServiceImpl orderServiceImpl;
	
	
	//---------------------------------------------------------------------登录、注册、注销-----------------------------------------------------------------------
	
	/**
	 * 前往登录页面
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/signin",method=RequestMethod.GET)
	public String goSignin(Map<String, Object> map){
		map.put("login", new Login()); 
		return "signin";
	}
	
	/**
	 * 登录处理
	 */
	@RequestMapping(value="/signin",method=RequestMethod.POST)
	public String signin(@Valid @ModelAttribute("login") Login login,BindingResult result,@RequestParam(value="orderUrl",required=false) String orderUrl, 
			HttpServletRequest request,Map<String,Object> map){
		//Hibernate Validator 验证非空
		if(result.getErrorCount() > 0){
			return "signin";
		}
		//验证码是否正确
		String sessionCaptcha = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(!login.getCaptcha().equals(sessionCaptcha)){
			result.rejectValue("captcha", "CaptchaError");
			return "signin";
		}
		Customer loginCustomer = customerServiceImpl.login(login.getUservalue(), login.getPwd());
		if(loginCustomer == null){
			result.rejectValue("pwd", "LoginError");
			return "signin";
		}
		customerServiceImpl.decodeCustomer(loginCustomer);
		map.put("authCustomer", loginCustomer);
		
		if(StringUtils.isNoneBlank(orderUrl)){
			return "redirect:"+orderUrl;
		}
		return "customerview/personalInfo";
	}
	
	
	/**
	 * 前往注册页面
	 */
	@RequestMapping(value="/signup",method=RequestMethod.GET)
	public String goSignup(Map<String, Object> map){
		map.put("customer", new Customer()); 
		return "signup";
	}
	
	
	/**
	 * 用户提交注册
	 * @param customer  用户填写表单后生成的Customer对象，属性为用户填写的值
	 * @param result  用于验证用户填写的参数是否有异常
	 * @return  若注册成功，则前往/WEB-INF/views/success.jsp
	 */
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public String signup(@Valid @ModelAttribute("customer") Customer customer,BindingResult result,
			Map<String,Object> map,@RequestParam("confirmpwd") String confirmpwd){
		String tmpUsername = customer.getNickname(); //用于日志（成功注册后用户名为密文，而日志中应放原文）
		//Hibernate Validator验证注册填写的值是不是符合要求(主要验证非空)
		if(result.getErrorCount() > 0){
//			for(FieldError error : result.getFieldErrors()){
//				System.out.println(error.getField()+":"+error.getDefaultMessage());
//			}
			return "signup";
		}
		//验证确认密码与密码相同
		if(StringUtils.isBlank(confirmpwd)){
			result.rejectValue("pwd", "ConfirmPwdBlankError");
			return "signup";
		}else{
			if(!(customer.getPwd().equals(confirmpwd))){
				result.rejectValue("pwd", "ConfirmPwdError");
				return "signup";
			}
		}
		//验证手机格式，用户名格式，用户名是否已注册，密码格式，邮箱格式是否正确
		if(!customerServiceImpl.validator(customer, result)){
			return "signup";
		}
		//用户信息加密
		customerServiceImpl.encodeCustomer(customer);
		//用户填写的值都符合要求时，保存用户数据
		if(customerServiceImpl.register(customer)){
			logger.info("添加了新用户:"+tmpUsername);
		}else{
			map.put("errorRegInfo", "系统处理异常，请您再次尝试 :) ");
			logger.error("用户注册异常");
			return "signup";
		}
		return "signupSuccess";
	}
	
	/**
	 * 在线用户注销
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/signout")
	public String signout(Map<String,Object> map){
		/*
		 * session中删除操作已放到SignoutInterceptors拦截器中完成
		 * SpringMVC对session进行了封装，和原生的HttpSession不一样！不可以@SessionAttributes和HttpSession混用。
		 * 删除属性时若用HttpSession无法删除。
		 */
		return "redirect:/customer/signin";
	}
	
	
	//---------------------------------------------------------------------个人信息-----------------------------------------------------------------------
	
	
	/**
	 * 前往个人信息页
	 * @param customer
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/info")
	public String goPersonalInfo(@ModelAttribute("authCustomer") Customer customer,Map<String,Object> map){
		map.put("authCustomer", customer); 
		return "customerview/personalInfo";
	}
	
	/**
	 * 修改基础信息
	 */
	@RequestMapping(value="/modify",method=RequestMethod.PUT)
	public String modifyInfo(@ModelAttribute("authCustomer") Customer customer,BindingResult result,Map<String,Object> map){
		//修改的参数有误
		if(!customerServiceImpl.validator_modify(customer, result)){
			//个人信息主页的显示是基于session的，即使修改的属性错的，但也会修过session中的值。所以，先将session恢复原貌。
			Customer originalCus = customerServiceImpl.getBasicInfo(customer.getCustomer_id());
			customerServiceImpl.decodeCustomer(originalCus);
			map.put("authCustomer", originalCus);
			map.put("modifyError", "<script type='text/javascript'>alert('填入的参数有误，修改失败。') </script>");
			return "customerview/personalInfo";
		}
		customerServiceImpl.modifyInfo(customer);
		customerServiceImpl.decodeCustomer(customer);
		map.put("modifySuc", "<script type='text/javascript'>alert('信息修改成功！') </script>");
		return "redirect:/customer/info";
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping(value="/modifyPwd")
	public String modifyPwd(@RequestParam("oldpwd") String oldpwd ,@RequestParam("newpwd") String newpwd ,
			@RequestParam("confirmpwd") String confirmpwd ,@ModelAttribute("authCustomer") Customer customer,Map<String,Object> map){
		
		String target = EncodeUtils.SHA1Encode(customer.getPwdsalt()+oldpwd);
		if(!target.equals(customer.getPwd())){
			map.put("modifyPwdError", "<script type='text/javascript'>alert('Error:您填写的旧密码错误！') </script>");
		}
		else{
			if(!confirmpwd.equals(newpwd)){
				map.put("modifyPwdError", "<script type='text/javascript'>alert('Error:新密码与确认密码不相同！') </script>");
			}
			else{
				int pwdLen = newpwd.length();
				if(!(pwdLen >= 6) && (pwdLen<= 14)){
					map.put("modifyPwdError", "<script type='text/javascript'>alert('Error:密码长度应在6-14位！') </script>");
				}
				else{
					customerServiceImpl.modifyPwd(customer, newpwd);
					customerServiceImpl.decodeCustomer(customer);
					map.put("modifyPwdSuc", "<script type='text/javascript'>alert('密码修改成功！') </script>");
				}
			}
		}
		return "customerview/personalInfo";
	}

	/**
	 * 生成订单时验证码的认证（AJax）
	 */
	@RequestMapping(value="/order/checkCaptcha")
	public void checkGenerateOrder(@RequestParam(value="captcha",required=false) String captcha,PrintWriter out,HttpSession session){
		String sessionCaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(sessionCaptcha.equals(captcha)){
			out.print("true");
		}else{
			out.println("false");
		}
	}
	
	
	//---------------------------------------------------------------------订单操作-----------------------------------------------------------------------

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
	 */
	@RequestMapping(value="/order/submitOrder&hotel_{hotelID}&roomType_{roomTypeID}",method=RequestMethod.POST)
	public String generateOrder(@PathVariable("hotelID") Integer hotelID,@PathVariable("roomTypeID") Integer roomTypeID,
			@ModelAttribute("authCustomer") Customer customer,RedirectAttributesModelMap map,
			@RequestParam("checkinDate") String checkinDate,@RequestParam("checkoutDate") String checkoutDate,
			@RequestParam("bookingNum") Integer bookingNum,@RequestParam("peopleNum") Integer peopleNum,@RequestParam("roomPrice") Double roomPrice,
			@RequestParam("hasChild") byte hasChild,@RequestParam("captcha") String captcha,
			@RequestParam("reservedName") String reservedName,@RequestParam("reservedPhone") String reservedPhone){
		
		//获得入住及退房日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date checkindate = null;
		Date checkoutdate = null;
		Date latestDate = null;
		try {
			 checkindate = sdf.parse(checkinDate);
			 checkoutdate = sdf.parse(checkoutDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return "redirect:/all";
		}
		
		//提交订单前务必先验证住的日子的客房是否已被预订满
		System.out.println("-----"+hotelServiceImpl.allowBookingOrNot(checkindate, checkoutdate, roomTypeID, bookingNum));
		if(!hotelServiceImpl.allowBookingOrNot(checkindate, checkoutdate, roomTypeID, bookingNum)){
				map.addFlashAttribute("OrderErrorInfo", "<script>alert('预订失败。该类型客房在您选择的时间段内已经被预订满！您可以调整预订数量或入住时间。');</script>");
				return "redirect:/customer/order/hotel_"+hotelID+"&roomType_"+roomTypeID;
		}
		
		//惯例：宾馆的入住时间为14：00，离店时间为正午12：00
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(checkindate);
		calendar.add(Calendar.HOUR, 14);  
		checkindate = calendar.getTime();  //入住时间
		
		calendar.setTime(checkindate);
		calendar.add(Calendar.HOUR, -14);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		latestDate = calendar.getTime(); //最晚入住时间（入住那天的24点）
		
		calendar.setTime(checkoutdate);
		calendar.add(Calendar.HOUR, 12);  
		checkoutdate = calendar.getTime();  //退房时间
		
		Order order = new Order();
		order.setOrderType(OrderStatusType.UNEXECUTED);
		order.setHotel(hotelServiceImpl.getHotelByID(hotelID));
		order.setCustomerID(customer.getCustomer_id());
		order.setReservedName(reservedName);
		order.setReservedPhone(reservedPhone);
		order.setCreationDate(new Date());
		order.setCheckinDate(checkindate);
		order.setCheckoutDate(checkoutdate);
		order.setLatestDate(latestDate);
		order.setRoomType(roomServiceImpl.getRoomTypeByTypeID(roomTypeID));
		order.setBookingNum(bookingNum);
		order.setPeopleNum(peopleNum);
		order.setHasChild(hasChild);
		order.setTotal(DateUtils.getDaysDiff(checkoutdate, checkindate)*roomPrice*bookingNum);
		if(orderServiceImpl.generateOrder(order)){
			//使用RedirectAttributesModelMap可以在redirect时放Attribute，可防止重复提交！
			map.addFlashAttribute("orderInfo", "<script>alert('您已成功预订！到店支付房费"+order.getTotal()+"！');</script>");
			return "redirect:/all";
		}else{
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
		if(orderServiceImpl.updateOrderStatus(order, OrderStatusType.CANCELLED)){
			map.put("Info","<script type='text/javascript'>alert('取消订单成功！') </script>" );
		}else{
			map.put("Info","<script type='text/javascript'>alert('取消订单失败.') </script>" );
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
	
	
	//---------------------------------------------------------------------会员-----------------------------------------------------------------------
	
	/**
	 * 前往会员处理页
	 */
	@RequestMapping(value="goMemberHandle",method=RequestMethod.GET)
	public String goMemberHandle(@ModelAttribute("authCustomer") Customer customer,Map<String,Object> map){
		if(customer.getGrade() == 0){
			boolean beMember = false;
			map.put("beMember", beMember);
		}
		
		return "customerview/member";
	}
	
	
	/**
	 * 成为一般会员
	 */
	@RequestMapping(value="beMember",method=RequestMethod.GET)
	public String beMember(@ModelAttribute("authCustomer") Customer customer,Map<String,Object> map){
		customerServiceImpl.beMember(customer);
		customerServiceImpl.decodeCustomer(customer);
		return "customerview/member";
	}
	
	/**
	 * 成为VIP会员
	 */
	@RequestMapping(value="beVIPMember",method=RequestMethod.GET)
	public String beVIPMember(@ModelAttribute("authCustomer") Customer customer,Map<String,Object> map){
		if(customer.getGrade() == 0){
			map.put("beMember", false);
			map.put("VIPInfo", "<script>alert('请您先成为普通会员')</script>");
			return "customerview/member";
		}
		if(customer.getGrade() > 5){
			map.put("VIPInfo", "<script>alert('您已经是VIP会员了')</script>");
			return "customerview/member";
		}
		customerServiceImpl.beVIPMember(customer);
		customerServiceImpl.decodeCustomer(customer);
		return "pay";
	}
	
}
