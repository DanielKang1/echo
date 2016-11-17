package com.echo.dao.test;

import java.util.List;

import com.echo.dao.hotelpromotiondao.HotelPromotionDAO;
import com.echo.domain.po.HotelPromotionItem;
import com.echo.domain.po.PromotionDate;

public class HotelPromotionDAO_stub implements HotelPromotionDAO{

	@Override
	public boolean add(HotelPromotionItem hotelPromotion) {
		System.out.println("save successfully!");
		return true;
	}

	@Override
	public boolean update(HotelPromotionItem hotelPromotion) {
		System.out.println("update successfully!");
		return true;
	}

	@Override
	public HotelPromotionItem getHotelPromotionItem(int hotelID) {
		System.out.println("getHotelPromotionItem successfully!");
		return null;
	}

	@Override
	public List<PromotionDate> getPromotionDateList(int hotelID) {
		System.out.println("getPromotionDateList successfully!");
		return null;
	}

	@Override
	public boolean deleteDatesPromotion(int id) {
		System.out.println("deleteDatesPromotion deleteDatesPromotion successfully!");
		return false;
	}

	@Override
	public boolean add(PromotionDate promotionDate) {
		System.out.println("add  PromotionDate successfully!");
		return false;
	}

	@Override
	public boolean update(PromotionDate promotionDate) {
		System.out.println("update PromotionDate successfully!");
		return false;
	}

}
