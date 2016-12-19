package com.echo.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.echo.domain.po.Customer;
import com.echo.domain.vo.Login;

public class GenerateOrderInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Customer customer = (Customer)request.getSession().getAttribute("authCustomer");
		
//		String url_ = request.getRequestURL().toString();              //		>>>: http://localhost:8080/echo/customer/order/hotel_1&roomType_1
		String requestUri = request.getRequestURI();                  //		>>>: /echo/customer/order/hotel_1&roomType_1
		String contextPath = request.getContextPath();                //		>>>: /echo
		String url = requestUri.substring(contextPath.length());       //		>>>: /customer/order/hotel_1&roomType_1

		if(customer == null){
			request.setAttribute("orderUrl", url);
			request.setAttribute("login", new Login()); 
			request.setAttribute("notSignin", "<script>alert('您尚未登录，在完成登录后会前往酒店预订页。')</script>");
			request.getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(request, response);
			return false;
		}else{
			if(customer.getCredit() < 0){
			request.setAttribute("creditDeficiency", "<script>alert('您的信用值小于0！请您进行信用充值，否则无法生成订单！')</script>");
			request.setAttribute("authCustomer", customer); 
			request.getRequestDispatcher("/WEB-INF/views/customerview/personalInfo.jsp").forward(request, response);
			return false;
			}
		}
		
		return true;
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
