package com.echo.controller;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.echo.domain.po.CreditChangeItem;
import com.echo.domain.po.Customer;
import com.echo.domain.po.PromotionDate;
import com.echo.domain.po.WebMarketer;
import com.echo.domain.type.CreditOperationType;
import com.echo.domain.vo.Login;
import com.echo.service.creditservice.CreditServiceImpl;
import com.echo.service.customerservice.CustomerServiceImpl;
import com.echo.service.hotelpromotionservice.HotelPromotionServiceImpl;
import com.echo.service.hotelservice.HotelServiceImpl;
import com.echo.service.orderservice.OrderServiceImpl;
import com.echo.service.recoverabnormalorderservice.RecoverAbnormalOrderService;
import com.echo.service.webmarketerservice.WebMarketerServiceImpl;
import com.echo.service.webpromotionservice.WebPromotionServiceImpl;
import com.google.code.kaptcha.Constants;

/**
 * 网站营销人员的所有操作
 * （对于营销策略的操作拆分到了WebPromotionController中）
 *
 */
@SessionAttributes(value={"authWebMarketer"})
@RequestMapping("/marketer") 
@Controller
public class WebMarketerController {
	
	public static final Logger logger = Logger.getLogger(WebMarketerController.class);
	
	@Autowired
	private WebMarketerServiceImpl webMarketerServiceImpl;
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	@Autowired
	private CreditServiceImpl creditServiceImpl;
	@Autowired
	private HotelPromotionServiceImpl hotelPromotionServiceImpl;
	@Autowired
	private WebPromotionServiceImpl webPromotionServiceImpl;
	@Autowired
	private HotelServiceImpl hotelServiceImpl;
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	@Autowired
	private RecoverAbnormalOrderService recoverAbnormalOrderService;
	
	/**
	 * 前往登录页
	 */
	@RequestMapping(value="/signin",method=RequestMethod.GET)
	public String goSignin(Map<String, Object> map){
		map.put("login", new Login()); 
		return "webmarketerview/marketerSignin";
	}
	
	/**
	 * WebMarketer登录操作
	 */
	@RequestMapping(value="/signin",method=RequestMethod.POST)
	public String signin(@Valid @ModelAttribute("login") Login login,BindingResult result, HttpSession session,Map<String,Object> map){
		if(result.getErrorCount() > 0){
			return "webmarketerview/marketerSignin";
		}
		//验证码是否正确
		String sessionCaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(!login.getCaptcha().equals(sessionCaptcha)){
			result.rejectValue("captcha", "CaptchaError");
			return "webmarketerview/marketerSignin";
		}
		WebMarketer marketer = webMarketerServiceImpl.login(login.getUservalue(), login.getPwd());
		if(marketer == null){
			result.rejectValue("pwd", "LoginError");
			return "webmarketerview/marketerSignin";
		}
		webMarketerServiceImpl.decodeWebMarketer(marketer);
		map.put("authWebMarketer", marketer);
		logger.info("WebMarketer登录："+marketer.getMarketerID()+" "+marketer.getMarketerName());
		return "redirect:/marketer/webMarketerHome";
	}
	
	/**
	 * 前往Marketer主页
	 * @param marketer
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/webMarketerHome")
	public String goWebMarketerHome(@ModelAttribute("authWebMarketer") WebMarketer marketer,Map<String, Object> map){
		List<PromotionDate> prodates = hotelPromotionServiceImpl.getHotelPromotionDateList(0);
		map.put("prodates", prodates);
		map.put("memberDiscounts", webPromotionServiceImpl.getAllMemberDiscount());
		map.put("cities", hotelServiceImpl.getCities());
		map.put("districtDiscounts", webPromotionServiceImpl.getAllDistrictDiscount());
		map.put("orders", orderServiceImpl.getAbnormalOrdersToday());
		return "webmarketerview/marketerHome";
	}
	
	/**
	 * Marketers退出
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/signout")
	public String signout(Map<String,Object> map){
		/*
		 * session中删除操作已放到SignoutInterceptors拦截器中完成
		 */
		return "redirect:/marketer/signin";
	}
	
	/**
	 * 获取用户信息（为充值做准备）
	 * @param name
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getcus")
	public String getCustomer(@RequestParam("name") String name,Map<String,Object> map){
		Customer customer = customerServiceImpl.getBasicInfo(name.trim());
		if(customer != null){
			customerServiceImpl.decodeCustomer(customer);
			map.put("customer", customer);
		}
		map.put("flag", true);
		return "forward:/marketer/webMarketerHome";
	}
	
	/**
	 * 用户信用充值
	 * @param rechargeNum 充值数额
	 * @param cid 用户ID
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/recharge")
	public String recharge(@RequestParam("rechargeNum") double rechargeNum,@RequestParam("cid") int cid,RedirectAttributesModelMap map){
		Customer customer = customerServiceImpl.getBasicInfo(cid);
		if(customer != null){
			CreditChangeItem creditChangeItem = new CreditChangeItem();
			creditChangeItem.setCustomerID(cid);
			creditChangeItem.setOperationType(CreditOperationType.RECHARGE);
			creditChangeItem.setAmount(rechargeNum * 100); 
			creditChangeItem.setOldResult(customer.getCredit() + rechargeNum * 100);
			creditChangeItem.setHotelID(0);
			creditChangeItem.setHotelName("");
			creditChangeItem.setOrderID(0);
			creditChangeItem.setChangeDate(new Date());
			if(creditServiceImpl.generateItem(creditChangeItem)){
				customerServiceImpl.modifyCredit(customer, rechargeNum * 100);
				logger.info("用户需要充值  用户ID："+creditChangeItem.getCustomerID()+" 充值数额："+creditChangeItem.getAmount());
				map.addFlashAttribute("info", "充值成功");
			}else{
				logger.error("用户信用充值失败");
			}
		}
		return "redirect:/marketer/webMarketerHome";
	}
	
	
	/**
	 * 撤销异常订单，恢复用户信用值
	 * @param type 恢复的类型：1为恢复一半，2为恢复全部
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/abnormalrecover{type}/{id}",method=RequestMethod.GET)
	public String recoverAbnormalOrder(@PathVariable("type") int type,@PathVariable("id") int id,RedirectAttributesModelMap map){
		boolean flag = recoverAbnormalOrderService.recoverAbnormalOrder(id, type);
		if(flag){ //中间未出现差错
			map.addFlashAttribute("info","该用户的信用值已恢复");
			logger.info("成功撤销异常订单 订单ID："+id);
		}
		else{
			logger.error("撤销异常订单失败 订单ID："+id);
		}
		return "redirect:/marketer/webMarketerHome";
	}
			
}
