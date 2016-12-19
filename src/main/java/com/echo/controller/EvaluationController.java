package com.echo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.echo.domain.po.Customer;
import com.echo.domain.po.Evaluation;
import com.echo.domain.po.Hotel;
import com.echo.domain.po.Order;
import com.echo.domain.type.OrderStatusType;
import com.echo.service.evaluationservice.EvaluationServiceImpl;
import com.echo.service.hotelservice.HotelServiceImpl;
import com.echo.service.orderservice.OrderServiceImpl;

/**
 * 评论 Controller
 */
@Controller
public class EvaluationController {
	
	public static final Logger logger = Logger.getLogger(EvaluationController.class);
	
	@Autowired
	public OrderServiceImpl orderServiceImpl;
	
	@Autowired
	public HotelServiceImpl hotelServiceImpl;
	
	@Autowired
	public EvaluationServiceImpl evaluationServiceImpl;
	
	/**
	 * 查看用户已有评论
	 */
	@RequestMapping(value="goViewEvaluations",method=RequestMethod.GET)
	public String goViewEvaluations(HttpServletRequest request,Map<String,Object> map){
		Customer customer = (Customer)request.getSession().getAttribute("authCustomer");
		List<Evaluation> res = evaluationServiceImpl.getCusEva(customer.getCustomer_id());
		Map<Evaluation,Hotel> resmap = new HashMap<>();
		for(Evaluation eva : res){
			Hotel hotel = hotelServiceImpl.getHotelByID(eva.getHotelID());
			resmap.put(eva, hotel);
		}
		map.put("alleva", resmap);
		return "customerview/evaluation";
	}
	
	/**
	 * 前往添加评论
	 */
	@RequestMapping(value="eva/{orderID}",method=RequestMethod.GET)
	public String goAddEvaluations(@PathVariable("orderID") int orderID,Map<String,Object> map){
		Order order = orderServiceImpl.getOrderByID(orderID);
		if(order.getOrderType() != OrderStatusType.EXECUTED){
			map.put("Info", "<script>alert('您不可以进行评论');</script>");
			return "forward:/customer/goViewOrders";
		}
		map.put("order", order);
		return "customerview/addEvaluation";
	}
	
	/**
	 * 添加评论
	 */
	@RequestMapping(value="eva",method=RequestMethod.POST)
	public String Evaluate(@RequestParam("score") int score,@RequestParam("advantage") String advantage,@RequestParam("orderID") int orderID,RedirectAttributesModelMap map,
			@RequestParam("disadvantage") String disadvantage,@RequestParam("comment") String comment ,HttpServletRequest request){
		Order order = orderServiceImpl.getOrderByID(orderID);
		Evaluation eva = new Evaluation();
		Customer customer = (Customer)request.getSession().getAttribute("authCustomer");
		eva.setComment(comment);
		eva.setDemerit(disadvantage);
		eva.setMerit(advantage);
		eva.setMark(score);
		eva.setReleaseTime(new Date());
		eva.setRoomTypeName(order.getRoomType().getTypeName());
		eva.setHotelID(order.getHotel().getHotelID());
		eva.setCustomerID(customer.getCustomer_id());
		eva.setCustomerName(customer.getNickname());
		if(evaluationServiceImpl.generateEva(eva)){
			orderServiceImpl.updateOrderStatus(order, OrderStatusType.EVALUATED);
			logger.info("添加评论 用户ID："+customer.getCustomer_id()+" 订单ID："+order.getOrderID());
			map.addFlashAttribute("info", "<script>alert('您的评论已成功添加！');</script>");
		}else{
			logger.error("添加评论失败 用户ID："+customer.getCustomer_id()+" 订单ID："+order.getOrderID());
			map.addFlashAttribute("info", "<script>alert('您的评论添加失败！');</script>");
		}
		return "redirect:/customer/goViewOrders";
	}
	
}
