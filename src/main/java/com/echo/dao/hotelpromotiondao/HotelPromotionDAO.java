package com.echo.dao.hotelpromotiondao;

import java.util.List;

import com.echo.domain.po.HotelPromotionItem;
import com.echo.domain.po.PromotionDate;

public interface HotelPromotionDAO {
	
	public HotelPromotionItem getHotelPromotionItem(int hotelID);
	
	public boolean add(HotelPromotionItem hotelPromotionItem);
	
	public boolean update(HotelPromotionItem hotelPromotionItem);
	
//	public boolean deleteHotelPromotionItem(int id);
	
	public List<PromotionDate> getPromotionDateList(int hotelID);
	
	public boolean update(PromotionDate promotionDate);
	
	public boolean deleteDatesPromotion(int id);
	
	public boolean add(PromotionDate promotionDate);

}
