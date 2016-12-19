package com.echo.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.echo.domain.po.Customer;
import com.echo.domain.po.Hotel;
import com.echo.domain.po.HotelStaff;
import com.echo.domain.po.WebAdmin;
import com.echo.domain.po.WebMarketer;
import com.echo.domain.vo.Login;
import com.echo.service.customerservice.CustomerServiceImpl;
import com.echo.service.hotelservice.HotelServiceImpl;
import com.echo.service.hotelstaffservice.HotelStaffServiceImpl;
import com.echo.service.webadminservice.WebAdminServiceImpl;
import com.echo.service.webmarketerservice.WebMarketerServiceImpl;
import com.echo.utils.DESUtils;
import com.google.code.kaptcha.Constants;

@SessionAttributes(value={"authWebAdmin"})
@RequestMapping("/admin") 
@Controller
public class WebAdminController {
	
	public static final Logger logger = Logger.getLogger(WebAdminController.class);
	
	@Autowired
	private WebAdminServiceImpl adminServiceImpl;
	@Autowired
	private HotelStaffServiceImpl hotelStaffServiceImpl;
	@Autowired
	private HotelServiceImpl hotelServiceImpl;
	@Autowired
	private WebMarketerServiceImpl webMarketerImpl;
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	/**
	 * 前往登录页
	 */
	@RequestMapping(value="/signin",method=RequestMethod.GET)
	public String goSignin(Map<String, Object> map){
		map.put("login", new Login()); 
		return "webadminview/adminSignin";
	}
	
	/**
	 * 登录操作
	 */
	@RequestMapping(value="/signin",method=RequestMethod.POST)
	public String signin(@Valid @ModelAttribute("login") Login login,BindingResult result, HttpSession session,Map<String,Object> map){
		if(result.getErrorCount() > 0){
			return "webadminview/adminSignin";
		}
		//验证码是否正确
		String sessionCaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(!login.getCaptcha().equals(sessionCaptcha)){
			result.rejectValue("captcha", "CaptchaError");
			return "webadminview/adminSignin";
		}
		WebAdmin admin = adminServiceImpl.login(login.getUservalue(), login.getPwd());
		if(admin == null){
			result.rejectValue("pwd", "LoginError");
			return "webadminview/adminSignin";
		}
		adminServiceImpl.decodeWebAdmin(admin);
		map.put("authWebAdmin", admin);
		logger.info("管理员登录");
		return "redirect:/admin/adminHome";
	}
	
	/**
	 * 管理员退出，session的处理在interceptor中
	 */
	@RequestMapping(value="/signout")
	public String signout(){
		return "redirect:/admin/signin";
	}
	
	/**
	 * 	前往管理主页
	 */
	@RequestMapping(value="/adminHome")
	public String goWebMarketerHome(@ModelAttribute("authWebMarketer") WebAdmin admin,Map<String, Object> map){
		map.put("cities", hotelServiceImpl.getCities());
		return "webadminview/adminHome";
	}
	
	/**
	 * Customer搜索
	 */
	@RequestMapping(value="/search_cus")
	public String searchCustomer(@RequestParam("name") String name,Map<String,Object> map){
		Customer customer = customerServiceImpl.getBasicInfo(name);
		if(customer != null){
			customerServiceImpl.decodeCustomer(customer);
			map.put("sea_customer", customer);
		}
		map.put("flag1", true);
		return "forward:/admin/adminHome";
	}
	
	/**
	 * 更新Customer信息
	 */
	@RequestMapping(value="/update_cus")
	public String updateCustomer(@RequestParam("email") String email,@RequestParam("phone") String phone,@RequestParam("cid") int id,Map<String,Object> map){
		Customer customer = customerServiceImpl.getBasicInfo(id);
		customerServiceImpl.decodeCustomer(customer);
		
		customer.setPhone(phone);
		customer.setEmail(email);
		if(customerServiceImpl.modifyInfo(customer)){
			map.put("Info", "信息更新成功");
			logger.info("管理员更新Customer信息，Customer ID："+customer.getCustomer_id());
		}else{
			logger.error("管理员更新Customer信息失败");
		}
		return "forward:/admin/adminHome";
	}
	
	/**
	 * HotelStaff搜索
	 */
	@RequestMapping(value="/search_staff")
	public String searchStaff(@RequestParam("name") String name,Map<String,Object> map){
		HotelStaff staff = hotelStaffServiceImpl.getBasicInfo(name);
		if(staff != null){
			hotelStaffServiceImpl.decodeHotelStaff(staff);
			map.put("sea_staff", staff);
			map.put("sea_staff_hotel", hotelServiceImpl.getHotelByID(staff.getHotelID()));
		}
		map.put("flag2", true);
		return "forward:/admin/adminHome";
	}
	
	/**
	 * 更新Staff信息
	 */
	@RequestMapping(value="/update_staff")
	public String updateStaff(@RequestParam("phone") String phone,@RequestParam("name") String name,@RequestParam("sid") int id,Map<String,Object> map){
		HotelStaff staff = hotelStaffServiceImpl.getBasicInfo(id);
		hotelStaffServiceImpl.decodeHotelStaff(staff);
		
		staff.setStaffName(DESUtils.getEncryptString(name));
		staff.setPhone(DESUtils.getEncryptString(phone));
		
		if(hotelStaffServiceImpl.updateStaff(staff)){
			map.put("Info", "信息更新成功");
			logger.info("管理员更新HotelStaff信息，HotelStaff ID："+staff.getStaffID()+" 所在HotelID："+staff.getHotelID());
		}else{
			logger.error("更新HotelStaff信息失败");
		}
		return "forward:/admin/adminHome";
	}
	
	/**
	 * webMarketer搜索
	 */
	@RequestMapping(value="/search_marketer")
	public String searchMarketer(@RequestParam("name") String name,Map<String,Object> map){
		WebMarketer marketer = webMarketerImpl.getBasicInfo(name);
		if(marketer != null){
			webMarketerImpl.decodeWebMarketer(marketer);
			map.put("sea_marketer", marketer);
		}
		map.put("flag3", true);
		return "forward:/admin/adminHome";
	}
	
	/**
	 * 更新webMarketer信息
	 */
	@RequestMapping(value="/update_mar")
	public String updateMarketer(@RequestParam("mid") int id,@RequestParam("name") String name,Map<String,Object> map){
		WebMarketer marketer = webMarketerImpl.getBasicInfo(id);
		webMarketerImpl.decodeWebMarketer(marketer);
		marketer.setMarketerName(DESUtils.getEncryptString(name));
		if(webMarketerImpl.updateMarketer(marketer)){
			map.put("Info", "信息更新成功");
			logger.info("管理员更新webMarketer信息，webMarketer ID："+marketer.getMarketerID());
		}else{
			 logger.error("更新webMarketer信息失败");
		}
		return "forward:/admin/adminHome";
	}
	
	/**
	 * 添加新的Marketer
	 */
	@RequestMapping(value="/add_mar")
	public String addMarketer(RedirectAttributesModelMap map,@RequestParam("name") String name,@RequestParam("pwd") String pwd){
		WebMarketer marketer = new WebMarketer();
		marketer.setMarketerName(name);
		marketer.setPwd(pwd);
		if(webMarketerImpl.addMarketer(marketer)){
			map.addFlashAttribute("Info", "添加成功");
			logger.info("添加新的Marketer："+marketer.getMarketerName());
		}else{
			 logger.error("添加Marketer失败");
		}
		return "redirect:/admin/adminHome";
	}
	
	/**
	 * 添加新的Hotel
	 */
	@RequestMapping(value="/add_hotel")
	public String addHotel(RedirectAttributesModelMap map,@RequestParam("hotelName") String hotelName,@RequestParam("city") String city,
			@RequestParam("staffName") String staffName,@RequestParam("staffPhone") String staffPhone,@RequestParam("staffPwd") String staffPwd,
			@RequestParam("district") String district,@RequestParam("starLevel") byte starLevel){
		Hotel hotel = new Hotel(hotelName, city, district, starLevel);
		int insertID = 0;
		if((insertID = hotelServiceImpl.addHotelReturnID(hotel)) > 0 ){
			 if(hotelStaffServiceImpl.addStaff(insertID, staffPhone, staffName, staffPwd)){
				 map.addFlashAttribute("Info", "添加成功");
				 logger.info("添加新的Hotel："+hotel.getHotelID());
			 }else{
				 logger.error("添加Hotel失败");
			 }
		}
		return "redirect:/admin/adminHome";
	}
	
	/**
	 * 搜索酒店信息
	 */
	@RequestMapping(value="/search_hotel")
	public String searchHotel(@RequestParam("hotelName") String hotelName,Map<String,Object> map){
		Hotel hotel = hotelServiceImpl.getHotelByName(hotelName.trim());
		if(hotel != null){
			map.put("hotel", hotel);
			HotelStaff staff = hotelStaffServiceImpl.getBasicInfoByHotelID(hotel.getHotelID());
			if(staff != null){
				map.put("staff",hotelStaffServiceImpl.decodeHotelStaff(staff));
			}
		}
		map.put("flag4", true);
		return "forward:/admin/adminHome";
	}

}
