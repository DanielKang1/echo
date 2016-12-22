package com.echo.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.echo.domain.po.DistrictDiscount;
import com.echo.domain.po.MemberDiscount;
import com.echo.domain.po.PromotionDate;
import com.echo.domain.po.WebMarketer;
import com.echo.service.hotelpromotionservice.HotelPromotionServiceImpl;
import com.echo.service.webpromotionservice.WebPromotionServiceImpl;

@SessionAttributes(value={"authWebMarketer"})
@RequestMapping("/marketer") 
@Controller
public class WebPromotionController {
	
	public static final Logger logger = Logger.getLogger(WebPromotionController.class);
	
	@Autowired
	private HotelPromotionServiceImpl hotelPromotionServiceImpl;
	@Autowired
	private WebPromotionServiceImpl webPromotionServiceImpl;
	
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
	

}
