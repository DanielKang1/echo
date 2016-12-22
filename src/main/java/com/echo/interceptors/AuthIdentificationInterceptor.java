package com.echo.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthIdentificationInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String requestUri = request.getRequestURI();                  
		String contextPath = request.getContextPath();                 
		String url = requestUri.substring(contextPath.length());
		String url_ = url.substring(1);

		if(url_.startsWith("customer")){
			if(request.getSession().getAttribute("authCustomer") == null){
				response.sendRedirect("/echo/404.jsp");
				return false;
			}
		}
		
		if(url_.startsWith("hotelstaff")){
			if(request.getSession().getAttribute("authHotelStaff") == null){
				response.sendRedirect("/echo/404.jsp");
				return false;
			}
		}
		
		if(url_.startsWith("admin")){
			if(request.getSession().getAttribute("authWebAdmin") == null){
				response.sendRedirect("/echo/404.jsp");
				return false;
			}
		}
		
		if(url_.startsWith("marketer")){
			if(request.getSession().getAttribute("authWebMarketer") == null){
				response.sendRedirect("/echo/404.jsp");
				return false;
			}
		}
		
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
