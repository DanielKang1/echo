package com.echo.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.echo.domain.po.Customer;

public class CheckBirthdayInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Customer customer = (Customer)request.getSession().getAttribute("authCustomer");
		System.out.println("99999999999999999-----------------------------------------------------"+customer);
		if(customer.getBirthday() == null){
			boolean beMember = false;
			request.setAttribute("beMember", beMember);//beMember是用于检测是否是会员。这里由于没有注册日期，提示信息后会重新回到会员页面，则还需要带一次beMember
			request.setAttribute("memberInfo", "<script>alert('您尚未登记生日，请您先登记一下生日以享受酒店生日特惠折扣！O(∩_∩)O')</script>");
			request.getRequestDispatcher("/WEB-INF/views/customerview/member.jsp").forward(request, response);
			return false;
		}
		else{
			request.setAttribute("memberInfo", "<script>alert('您已成为普通会员O(∩_∩)O')</script>");
			return true;
		}
		
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
