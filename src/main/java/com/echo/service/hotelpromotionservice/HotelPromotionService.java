package com.echo.service.hotelpromotionservice;

import java.util.List;

import com.echo.domain.po.HotelPromotionItem;
import com.echo.domain.po.PromotionDate;

public interface HotelPromotionService{
	
	 
	public boolean addHotelPromotionItem(HotelPromotionItem hotelPromotionItem);
	
	public boolean updateHotelPromotionItem(HotelPromotionItem hotelPromotionItem);
	
	public HotelPromotionItem getHotelPromotionItem(int hotelID);
	
	
	public List<PromotionDate> getHotelPromotionDateList(int hotelID);
	
	public boolean updatePromotionDateItem(PromotionDate promotionDate);
	
	public boolean deletePromotionDateItem(int id);
	
	public boolean addPromotionDateItem(PromotionDate promotionDate);
	
}
