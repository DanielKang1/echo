package com.echo.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SignoutInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String requestUri = request.getRequestURI();                  
		String contextPath = request.getContextPath();                 
		String url = requestUri.substring(contextPath.length());
		String url_ = url.substring(1);

		if(url_.startsWith("hotelstaff")){
			request.getSession().removeAttribute("authHotelStaff");
		}
		if(url_.startsWith("customer")){
			request.getSession().removeAttribute("authCustomer");
		}
		if(url_.startsWith("admin")){
			request.getSession().removeAttribute("authWebAdmin");
		}
		if(url_.startsWith("marketer")){
			request.getSession().removeAttribute("authWebMarketer");
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
