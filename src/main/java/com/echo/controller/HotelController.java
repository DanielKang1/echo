package com.echo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.echo.domain.po.District;
import com.echo.domain.po.Hotel;
import com.echo.domain.po.RoomType;
import com.echo.domain.vo.HotelSearchResult;
import com.echo.domain.vo.HotelSearcher;
import com.echo.domain.vo.RoomSearchResult;
import com.echo.service.evaluationservice.EvaluationServiceImpl;
import com.echo.service.hotelservice.HotelServiceImpl;
import com.echo.service.roomservice.RoomServiceImpl;

@Controller
public class HotelController {
	
	@Autowired
	private HotelServiceImpl hotelServiceImpl;
	
	@Autowired
	private EvaluationServiceImpl evaluationServiceImpl;
	
	@Autowired
	private RoomServiceImpl roomServiceImpl;
	
	/**
	 * 前往主页
	 */
	@RequestMapping("/all")
	public String goAllHotelsView(Map<String,Object> map){
		map.put("hotelSearcher", new HotelSearcher());
		map.put("cities", hotelServiceImpl.getCities());
		return "allHotels";
	}
	
	/**
	 * 前往酒店信息页
	 */
	@RequestMapping(value="/hotel_{id}",method=RequestMethod.GET)
	public String goHotelInfo (@PathVariable("id") Integer id,Map<String,Object> map){
		Hotel hotel = hotelServiceImpl.getHotelByID(id);
		if(hotel == null){
			//无该ID酒店，则前往主页
			return "forward:/all";
		}
		List<RoomType> res = roomServiceImpl.getRoomTypeByHotelID(hotel.getHotelID());
		map.put("hotel", hotel);
		map.put("roomResults", res);
		map.put("alleva", evaluationServiceImpl.getHotelEva(hotel.getHotelID()));
		return "hotelInfo";
	}

	/**
	 * 提供搜索时用的city列表
	 * @param city
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/city")
	public List<District> getDistricts(@RequestParam(value="city",required=false) String city){
		List<District> result = hotelServiceImpl.getDistrictsByCity(city);
		return result;
	}
	
	/**
	 * 搜索处理
	 */
	@RequestMapping("/searchHandle")
	public String searchHandle(@ModelAttribute("hotelSearcher") HotelSearcher hotelSearcher,
			@RequestParam(value="priceRange",required=false) String priceRange,Map<String,Object> map){
		
		if(StringUtils.isBlank(hotelSearcher.getDistrict())){
			map.put("SelectionReminder", "请选择城市和商圈:)");
			return "forward:/search";
		}
		
		double floor = 0;
		double ceiling = 0;
		if(hotelSearcher.getPriceCeiling() != 0){
			floor = hotelSearcher.getPriceFloor();
			ceiling = hotelSearcher.getPriceCeiling();
		}else{
			int priceIndex = Integer.parseInt(priceRange); // 0 1 2 3 4 5
			double[] floorArray = {0 , 0 ,151, 301, 601 ,1001};
			double[] ceilingArray = {99999999 ,150 ,300, 600, 1000 ,99999999};
			floor = floorArray[priceIndex];
			ceiling = ceilingArray[priceIndex];
		}
		hotelSearcher.setPriceFloor(floor);
		hotelSearcher.setPriceCeiling(ceiling);
		List<HotelSearchResult> res = hotelServiceImpl.search(hotelSearcher);
		map.put("hotelSearchResultList", res);
		return "forward:/search";
	}
	
	/**
	 * 前往搜索页
	 */
	@RequestMapping("/search")
	public String goSearch(Map<String,Object> map){
		map.put("hotelSearcher", new HotelSearcher());
		map.put("cities", hotelServiceImpl.getCities());
		return "searchResult";
	}

}
