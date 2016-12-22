package com.echo.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.echo.domain.po.HotelPromotionItem;
import com.echo.domain.po.HotelStaff;
import com.echo.domain.po.PromotionDate;
import com.echo.service.hotelpromotionservice.HotelPromotionServiceImpl;

@SessionAttributes(value={"authHotelStaff"})
@RequestMapping("/hotelstaff") 
@Controller
public class HotelPromotionController {
	
	@Autowired
	private HotelPromotionServiceImpl hotelPromotionServiceImpl;
	
	/**
	 * 前往营销策略页
	 */
	@RequestMapping(value="/promotions",method=RequestMethod.GET)
	public String goPromotionPage(@ModelAttribute("authHotelStaff") HotelStaff hotelStaff,Map<String,Object> map){
		//可以在添加酒店时加入
//		if(hotelPromotionServiceImpl.getHotelPromotionItem(hotelStaff.getHotelID()) == null){
//			HotelPromotionItem item = new HotelPromotionItem(hotelStaff.getHotelID(), 1, false, 10, 1, false, 1, false);
//			hotelPromotionServiceImpl.addHotelPromotionItem(item);
//		}
		HotelPromotionItem hotelPromotionItem = hotelPromotionServiceImpl.getHotelPromotionItem(hotelStaff.getHotelID());
		List<PromotionDate> prodates = hotelPromotionServiceImpl.getHotelPromotionDateList(hotelStaff.getHotelID());
		map.put("hotelPromotionItem", hotelPromotionItem);
		map.put("prodates", prodates);
		return "hotelstaffview/promotions";
	}
	
	/**
	 * 开启/关闭 生日折扣
	 * @param checkbox1
	 * @param hotelStaff
	 * @return
	 */
	@RequestMapping(value="/bir_switch_update",method=RequestMethod.POST)
	public String birthdaySwitchUpdate(@RequestParam(value="checkbox1",required=false) Object checkbox1,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		HotelPromotionItem hotelPromotionItem = hotelPromotionServiceImpl.getHotelPromotionItem(hotelStaff.getHotelID());
		if(null == checkbox1){
			hotelPromotionItem.setBirthdaySwitch(false);
			hotelPromotionServiceImpl.updateHotelPromotionItem(hotelPromotionItem);
		}else{
			hotelPromotionItem.setBirthdaySwitch(true);
			hotelPromotionServiceImpl.updateHotelPromotionItem(hotelPromotionItem);
		}
		return "redirect:/hotelstaff/promotions";
	}
	
	/**
	 * 更新生日折扣信息
	 * @param discount
	 * @param hotelStaff
	 * @return
	 */
	@RequestMapping(value="/bir_update",method=RequestMethod.POST)
	public String birthdayUpdate(@RequestParam(value="discount") float discount,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		HotelPromotionItem hotelPromotionItem = hotelPromotionServiceImpl.getHotelPromotionItem(hotelStaff.getHotelID());
		hotelPromotionItem.setBirthdayDiscount(discount);
		hotelPromotionServiceImpl.updateHotelPromotionItem(hotelPromotionItem);
		return "redirect:/hotelstaff/promotions";
	}
	
	/**
	 * 开启/关闭 预订特定数量折扣
	 * @param checkbox2
	 * @param hotelStaff
	 * @return
	 */
	@RequestMapping(value="/num_switch_update",method=RequestMethod.POST)
	public String bookingNumSwitchUpdate(@RequestParam(value="checkbox2",required=false) Object checkbox2,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		HotelPromotionItem hotelPromotionItem = hotelPromotionServiceImpl.getHotelPromotionItem(hotelStaff.getHotelID());
		if(null == checkbox2){
			hotelPromotionItem.setBookingDiscountSwitch(false);
			hotelPromotionServiceImpl.updateHotelPromotionItem(hotelPromotionItem);
		}else{
			hotelPromotionItem.setBookingDiscountSwitch(true);
			hotelPromotionServiceImpl.updateHotelPromotionItem(hotelPromotionItem);
		}
		return "redirect:/hotelstaff/promotions";
	}
	/**
	 * 更新预订特定数量折扣信息 
	 * @param discount
	 * @param number
	 * @param hotelStaff
	 * @return
	 */
	@RequestMapping(value="/num_update",method=RequestMethod.POST)
	public String bookingNumUpdate(@RequestParam(value="discount") float discount,@RequestParam(value="number") int number,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		HotelPromotionItem hotelPromotionItem = hotelPromotionServiceImpl.getHotelPromotionItem(hotelStaff.getHotelID());
		hotelPromotionItem.setBookingDiscount(discount);
		hotelPromotionItem.setBookingMeasure(number);
		hotelPromotionServiceImpl.updateHotelPromotionItem(hotelPromotionItem);
		return "redirect:/hotelstaff/promotions";
	}
	
	/**
	 * 开启/关闭  合作企业折扣
	 * @param checkbox3
	 * @param hotelStaff
	 * @return
	 */
	@RequestMapping(value="/coo_switch_update",method=RequestMethod.POST)
	public String cooUserSwitchUpdate(@RequestParam(value="checkbox3",required=false) Object checkbox3,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		HotelPromotionItem hotelPromotionItem = hotelPromotionServiceImpl.getHotelPromotionItem(hotelStaff.getHotelID());
		if(null == checkbox3){
			hotelPromotionItem.setCooperativeEnterpriseSwitch(false);
			hotelPromotionServiceImpl.updateHotelPromotionItem(hotelPromotionItem);
		}else{
			hotelPromotionItem.setCooperativeEnterpriseSwitch(true);
			hotelPromotionServiceImpl.updateHotelPromotionItem(hotelPromotionItem);
		}
		return "redirect:/hotelstaff/promotions";
	}
	
	/**
	 * 更新合作企业折扣
	 * @param discount
	 * @param hotelStaff
	 * @return
	 */
	@RequestMapping(value="/coo_update",method=RequestMethod.POST)
	public String cooUpdate(@RequestParam(value="discount") float discount,@ModelAttribute("authHotelStaff") HotelStaff hotelStaff){
		HotelPromotionItem hotelPromotionItem = hotelPromotionServiceImpl.getHotelPromotionItem(hotelStaff.getHotelID());
		hotelPromotionItem.setCooperativeEnterpriseDiscount(discount);
		hotelPromotionServiceImpl.updateHotelPromotionItem(hotelPromotionItem);
		return "redirect:/hotelstaff/promotions";
	}
	
	/**
	 * 添加特定日期段促销数据
	 * @param hotelStaff
	 * @param start 开始日期
	 * @param end  结束日期
	 * @param discount 折扣率
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/promotiondate",method=RequestMethod.POST)
	public String addPromotionDate(@ModelAttribute("authHotelStaff") HotelStaff hotelStaff,@RequestParam(value="start") String start,@RequestParam(value="end") String end,@RequestParam(value="discount") float discount) throws ParseException{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date start_ = dateFormat.parse(start);
		Date end_ = dateFormat.parse(end);
		PromotionDate promotionDate = new PromotionDate(hotelStaff.getHotelID(), start_, end_, discount);
		hotelPromotionServiceImpl.addPromotionDateItem(promotionDate);
		return "redirect:/hotelstaff/promotions";
	}
	
	/**
	 * 删除特定日期段促销数据
	 * @param hotelStaff
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/promotiondate/delete/{id}",method=RequestMethod.GET)
	public String deletePromotionDate(@ModelAttribute("authHotelStaff") HotelStaff hotelStaff,@PathVariable("id") int id){
		if(hotelStaff != null){
			List<PromotionDate> prodates = hotelPromotionServiceImpl.getHotelPromotionDateList(hotelStaff.getHotelID());
			boolean flag = false;
			for(PromotionDate pdate : prodates){
				if(pdate.getId() == id){
					flag = true;
					break;
				}
			}
			if(flag){
				hotelPromotionServiceImpl.deletePromotionDateItem(id);
			}
		}
		
		return "redirect:/hotelstaff/promotions";
	}
	

}
