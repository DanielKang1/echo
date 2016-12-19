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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.echo.domain.po.CreditChangeItem;
import com.echo.domain.po.Customer;
import com.echo.domain.po.DistrictDiscount;
import com.echo.domain.po.MemberDiscount;
import com.echo.domain.po.Order;
import com.echo.domain.po.PromotionDate;
import com.echo.domain.po.WebMarketer;
import com.echo.domain.type.CreditOperationType;
import com.echo.domain.type.OrderStatusType;
import com.echo.domain.vo.Login;
import com.echo.service.creditservice.CreditServiceImpl;
import com.echo.service.customerservice.CustomerServiceImpl;
import com.echo.service.hotelpromotionservice.HotelPromotionServiceImpl;
import com.echo.service.hotelservice.HotelServiceImpl;
import com.echo.service.orderservice.OrderServiceImpl;
import com.echo.service.webmarketerservice.WebMarketerServiceImpl;
import com.echo.service.webpromotionservice.WebPromotionServiceImpl;
import com.google.code.kaptcha.Constants;

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
		return "webmarketerview/marketerHome";
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
	 * 添加网站促销日期段
	 * @param start 开始日期
	 * @param end  结束日期
	 * @param discount 折扣率
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/promotiondate",method=RequestMethod.POST)
	public String addPromotionDate(@RequestParam("start") String start,@RequestParam("end") String end,@RequestParam("discount") float discount) throws ParseException{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date start_ = dateFormat.parse(start);
		Date end_ = dateFormat.parse(end);
		PromotionDate promotionDate = new PromotionDate(0, start_, end_, discount);
		try {
			hotelPromotionServiceImpl.addPromotionDateItem(promotionDate);
			logger.info("成功添加网站促销日期段："+promotionDate.getStartDate()+"-"+promotionDate.getEndDate()+" 折扣率："+promotionDate.getDiscount());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加网站促销日期段失败"+e);
		}
		return "redirect:/marketer/webMarketerHome";
	}
	
	/**
	 * 删除促销日期段
	 */
	@RequestMapping(value="/promotiondate/delete/{id}",method=RequestMethod.GET)
	public String deletePromotionDate(@ModelAttribute("authWebMarketer") WebMarketer marketer,@PathVariable("id") int id){
		if(marketer != null){
			List<PromotionDate> prodates = hotelPromotionServiceImpl.getHotelPromotionDateList(0);
			boolean flag = false;
			for(PromotionDate pdate : prodates){
				if(pdate.getId() == id){
					flag = true;
					break;
				}
			}
			if(flag){
				hotelPromotionServiceImpl.deletePromotionDateItem(id);
				logger.info("成功删除网站促销日期段   ID："+id);
			}else{
				logger.error("删除网站促销日期段失败 ID："+id);
			}
		}
		return "redirect:/marketer/webMarketerHome";
	}
	
	/**
	 * 前往 更新会员折扣页
	 * @param levelID
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/memberdiscount/goupdate/{levelID}",method=RequestMethod.GET)
	public String goUpdateMemberDiscount(@PathVariable("levelID") int levelID,Map<String,Object> map){
		MemberDiscount md = webPromotionServiceImpl.getMemberDiscountByLevelID(levelID);
		map.put("updateMD", md);
		return "forward:/marketer/webMarketerHome";
	}
	
	/**
	 * 更新会员折扣
	 */
	@RequestMapping(value="/memberdiscount/update",method=RequestMethod.POST)
	public String updateMemberDiscount(@RequestParam("levelName") String levelName,@RequestParam("discount") float discount,
			@RequestParam("creditLimit") float creditLimit,@RequestParam("levelID") int levelID,Map<String,Object> map){
		MemberDiscount md = webPromotionServiceImpl.getMemberDiscountByLevelID(levelID);
		md.setCreditLimit(creditLimit);
		md.setLevelName(levelName);
		md.setDiscount(discount);
		if(webPromotionServiceImpl.updateMemberDiscount(md)){
			map.put("info", "会员等级已成功更新");
			logger.info("会员等级已成功更新  ID："+md.getLevelID());
		}else{
			logger.error("会员等级更新失败  ID："+md.getLevelID());
		}
		return "forward:/marketer/webMarketerHome";
	}
	
	/**
	 * 添加会员折扣
	 */
	@RequestMapping(value="/memberdiscount/add",method=RequestMethod.POST)
	public String addMemberDiscount(@RequestParam("levelName") String levelName,@RequestParam("discount") float discount,@RequestParam("creditLimit") float creditLimit,RedirectAttributesModelMap map){
		MemberDiscount md = new MemberDiscount();
		md.setCreditLimit(creditLimit);
		md.setLevelName(levelName);
		md.setDiscount(discount);
		if(webPromotionServiceImpl.addMemberDiscount(md)){
			map.addFlashAttribute("info", "会员折扣添加成功");
			logger.info("会员等级添加成功  Name："+md.getLevelName());
		}else{
			logger.error("会员等级添加失败  Name："+md.getLevelName());
		}
		return "redirect:/marketer/webMarketerHome";
	}
	
	/**
	 * 删除会员折扣
	 */
	@RequestMapping(value="/memberdiscount/delete/{levelID}",method=RequestMethod.GET)
	public String deleteMemberDiscount(@PathVariable("levelID") int levelID,Map<String,Object> map){
		if(webPromotionServiceImpl.deleteMemberDiscount(levelID)){
			map.put("info", "该会员等级已删除");
			logger.info("会员等级删除成功  levelID："+levelID);
		}else{
			logger.error("会员等级删除失败  levelID："+levelID);
		}
		return "forward:/marketer/webMarketerHome";
	}
	
	/**
	 * 添加VIP特定商圈优惠Item
	 */
	@RequestMapping(value="/VIPdistrict",method=RequestMethod.POST)
	public String addVIPdistrict(@RequestParam("cityName") String cityName,@RequestParam("districtName") String districtName,@RequestParam("vip1") float vip1,@RequestParam("vip2") float vip2,
			@RequestParam("vip3") float vip3,@RequestParam("vip4") float vip4,@RequestParam("vip5") float vip5,RedirectAttributesModelMap map){
		DistrictDiscount districtDiscount = new DistrictDiscount(cityName, districtName, vip1, vip2, vip3, vip4, vip5);
		if(webPromotionServiceImpl.addDistrictDiscount(districtDiscount)){
			map.addFlashAttribute("info", "VIP会员特定商圈折扣 添加成功！");
			logger.info("成功添加VIP特定商圈优惠Item："+districtDiscount.getCityName()+" "+districtDiscount.getDistrictName());
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
		Order order = orderServiceImpl.getOrderByID(id);
		if(order != null){
			Customer customer = customerServiceImpl.getBasicInfo(order.getCustomerID());  //相关用户
			CreditChangeItem item = new CreditChangeItem(order.getCustomerID(), order.getOrderID(), CreditOperationType.ABNORMAL_CANCELLED,
					id, order.getHotel().getHotelName(),  order.getTotal());
			
			boolean flag = true;
			try {
				//1.更新订单状态
				orderServiceImpl.updateOrderStatus(order, OrderStatusType.CANCELLED);
				if(type == 1){
					item.setAmount(order.getTotal() / 2 );
				}
				//2.生成信用变化记录
				creditServiceImpl.generateItem(item);
				//3.修改用户信用值（包含了可能对用户级别的修改）
				customerServiceImpl.modifyCredit(customer, item.getAmount());
			} catch (Exception e) {
				flag = false;
				e.printStackTrace();
				logger.error("撤销异常订单失败 订单ID："+order.getOrderID());
			}
			
			if(flag){ //中间未出现差错
				map.addFlashAttribute("info","该用户的信用值已恢复");
				logger.info("成功撤销异常订单 订单ID："+order.getOrderID());
			}
		}
		return "redirect:/marketer/webMarketerHome";
	}
			
}
