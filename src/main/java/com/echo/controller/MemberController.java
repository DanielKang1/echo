package com.echo.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.echo.domain.po.CompanyMember;
import com.echo.domain.po.Customer;
import com.echo.service.customerservice.CustomerServiceImpl;
import com.echo.service.webpromotionservice.WebPromotionServiceImpl;

/**
 *用户关于会员的处理
 *（舍弃普通会员与VIP会员的区别，beMember与beVIPMember被删。 2016-12-21）
 */
@SessionAttributes(value={"authCustomer"})
@RequestMapping("/customer") 
@Controller
public class MemberController {
	
	public static final Logger logger = Logger.getLogger(MemberController.class);
	
//	@Autowired
//	private CustomerServiceImpl customerServiceImpl;
	
	@Autowired
	private WebPromotionServiceImpl webPromotionServiceImpl;
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	/**
	 * 前往会员处理页
	 */
	@RequestMapping(value="goMemberHandle",method=RequestMethod.GET)
	public String goMemberHandle(@ModelAttribute("authCustomer") Customer customer,Map<String,Object> map){
		if(customer.getBirthday() == null){
			map.put("Info", "<script>alert('请您填写生日信息，已享受生日特惠。')</script>");
		}
		map.put("memberDiscounts", webPromotionServiceImpl.getAllMemberDiscount());
		map.put("allDistrictDiscounts", webPromotionServiceImpl.getAllDistrictDiscounts());
		return "customerview/member";
	}
	
	/**
	 * 企业会员登记
	 * @return
	 */
	@RequestMapping(value="coomember",method=RequestMethod.POST)
	public String CompanyMemberHandle(@RequestParam("cooName") String cooName,RedirectAttributesModelMap map,@ModelAttribute("authCustomer") Customer customer){
		CompanyMember companyMember = new CompanyMember(customer.getCustomer_id(), cooName);
		if(customerServiceImpl.addCompanyMember(companyMember)){
			map.addFlashAttribute("Info", "<script>alert('您的信息已提交，请等待审核。')</script>");
		}
		return "redirect:/customer/goMemberHandle";
	}
	
	
	/**
	 * 成为一般会员
	 */
//	@RequestMapping(value="beMember",method=RequestMethod.GET)
//	public String beMember(@ModelAttribute("authCustomer") Customer customer,Map<String,Object> map){
//		try {
//			customerServiceImpl.beMember(customer);
//			customerServiceImpl.decodeCustomer(customer);
//			logger.info("用户"+customer.getCustomer_id()+" "+customer.getNickname()+"成为一般会员");
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("成为一般会员失败 ："+customer.getCustomer_id()+" "+customer.getNickname()+" "+e);
//		}
//		return "customerview/member";
//	}
	
	/**
	 * 成为VIP会员
	 */
//	@RequestMapping(value="beVIPMember",method=RequestMethod.GET)
//	public String beVIPMember(@ModelAttribute("authCustomer") Customer customer,Map<String,Object> map){
//		if(customer.getGrade() == 0){
//			map.put("beMember", false);
//			map.put("VIPInfo", "<script>alert('请您先成为普通会员')</script>");
//			return "customerview/member";
//		}
//		if(customer.getGrade() > 5){
//			map.put("VIPInfo", "<script>alert('您已经是VIP会员了')</script>");
//			return "customerview/member";
//		}
//		try {
//			customerServiceImpl.beVIPMember(customer);
//			customerServiceImpl.decodeCustomer(customer);
//			logger.info("用户"+customer.getCustomer_id()+" "+customer.getNickname()+"成为VIP会员");
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("成为VIP会员失败 ："+customer.getCustomer_id()+" "+customer.getNickname()+" "+e);
//		}
//		return "pay";
//	}
	

}
