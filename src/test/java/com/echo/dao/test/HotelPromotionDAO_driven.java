package com.echo.dao.test;

import com.echo.dao.hotelpromotiondao.HotelPromotionDAO;
import com.echo.domain.po.HotelPromotionItem;
import com.echo.domain.po.PromotionDate;

public class HotelPromotionDAO_driven {
	
	public void drive(HotelPromotionDAO hotelPromotionDAO){
		hotelPromotionDAO.add(new HotelPromotionItem(1, 0.9, 2, 0.9, 0.9));
		hotelPromotionDAO.add(new PromotionDate());
		hotelPromotionDAO.deleteDatesPromotion(1);
		hotelPromotionDAO.getHotelPromotionItem(1);
		hotelPromotionDAO.getPromotionDateList(1);
		hotelPromotionDAO.update(new HotelPromotionItem(1, 0.9, 2, 0.9, 0.9));
		hotelPromotionDAO.update(new PromotionDate());
	}
	
	public static void main(String[] args) {
		new HotelPromotionDAO_driven().drive(new HotelPromotionDAO_stub());
	}

}
