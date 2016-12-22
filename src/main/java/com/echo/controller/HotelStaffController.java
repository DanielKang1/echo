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
import com.echo.domain.po.Hotel;
import com.echo.domain.po.HotelStaff;
import com.echo.domain.vo.Login;
import com.echo.service.hotelservice.HotelServiceImpl;
import com.echo.service.hotelstaffservice.HotelStaffServiceImpl;
import com.google.code.kaptcha.Constants;


/**
 * 负责酒店工作人员的相关操作
 * （酒店营销操作已拆分到 HotelPromotionController中）
 * （客房的处理已拆分到RoomController中）
 * （酒店工作人员订单操作拆分到了HotelStaffOrderController中）
 * （入住/退房处理拆分到了CheckController中）
 * @author lenovo
 *
 */

@SessionAttributes(value={"authHotelStaff"})
@RequestMapping("/hotelstaff") 
@Controller
public class HotelStaffController {
	
	public static final Logger logger = Logger.getLogger(HotelStaffController.class);
	
	@Autowired
	private HotelStaffServiceImpl hotelstaffserviceImpl;
	
	@Autowired
	private HotelServiceImpl hotelServiceImpl;

	
	
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
 
	
}
