package com.echo.controller;

import java.io.PrintWriter;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.echo.domain.po.Customer;
import com.echo.domain.type.MemberLevelType;
import com.echo.domain.vo.Login;
import com.echo.service.creditservice.CreditServiceImpl;
import com.echo.service.customerservice.CustomerServiceImpl;
import com.echo.utils.EncodeUtils;
import com.google.code.kaptcha.Constants;

/**
 * 关于Customer的处理
 * （对于Customer的会员处理 拆分到了 MemberController中）
 * （对于Customer的订单处理 拆分到了CustomerOrderController中）
 */

@SessionAttributes(value={"authCustomer"})
@RequestMapping("/customer") 
@Controller
public class CustomerController {
	
	public static final Logger logger = Logger.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	@Autowired
	private CreditServiceImpl creditServiceImpl;
	
	
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
		logger.info("用户登录："+loginCustomer.getCustomer_id()+" "+loginCustomer.getNickname());
		return "redirect:/customer/info";
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
		customerServiceImpl.encodeCustomer(customer);  //用户信息加密
		customer.setGrade(MemberLevelType.Level1);  //初始级别为Level1
		//用户填写的值都符合要求时，保存用户数据
		if(customerServiceImpl.register(customer)){
			logger.info("新注册用户："+tmpUsername);
		}else{
			map.put("errorRegInfo", "系统处理异常，请您再次尝试 :) ");
			logger.error("注册异常");
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
		map.put("creditItems",creditServiceImpl.getCustomerItem(customer.getCustomer_id()));
		map.put("credit_", customerServiceImpl.getBasicInfo(customer.getCustomer_id()).getCredit());
		return "customerview/personalInfo";
	}
	
	/**
	 * 修改基础信息
	 */
	@RequestMapping(value="/modify",method=RequestMethod.PUT)
	public String modifyInfo(@ModelAttribute("authCustomer") Customer customer,BindingResult result,RedirectAttributesModelMap map){
		//修改的参数有误
		if(!customerServiceImpl.validator_modify(customer, result)){
			//个人信息主页的显示是基于session的，即使修改的属性错的，但也会修过session中的值。所以，先将session恢复原貌。
			Customer originalCus = customerServiceImpl.getBasicInfo(customer.getCustomer_id());
			customerServiceImpl.decodeCustomer(originalCus);
			map.addFlashAttribute("authCustomer", originalCus);
			map.addFlashAttribute("modifyError", "<script type='text/javascript'>alert('填入的参数有误，修改失败。') </script>");
			return "redirect:/customer/info";
		}
		try {
			customerServiceImpl.modifyInfo(customer);
			customerServiceImpl.decodeCustomer(customer);
			logger.info("Customer个人信息修改："+customer.getCustomer_id()+" "+customer.getNickname());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Customer个人信息修改异常："+e);
		}
		map.addFlashAttribute("modifySuc", "<script type='text/javascript'>alert('信息修改成功！') </script>");
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
					try {
						customerServiceImpl.modifyPwd(customer, newpwd);
						logger.info("Customer修改密码："+customer.getCustomer_id()+" "+customer.getNickname());
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("Customer密码修改异常："+e);
					}
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
	
}
