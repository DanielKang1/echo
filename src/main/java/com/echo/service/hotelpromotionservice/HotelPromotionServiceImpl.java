package com.echo.service.hotelpromotionservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.echo.dao.hotelpromotiondao.HotelPromotionDAOImpl;
import com.echo.domain.po.HotelPromotionItem;
import com.echo.domain.po.PromotionDate;

@Service
public class HotelPromotionServiceImpl implements HotelPromotionService{
	
	@Autowired
	private HotelPromotionDAOImpl hotelPromotionDAOImpl;

	@Override
	public List<PromotionDate> getHotelPromotionDateList(int hotelID) {
		return hotelPromotionDAOImpl.getPromotionDateList(hotelID);
	}

	@Override
	public boolean updatePromotionDateItem(PromotionDate promotionDate) {
		return hotelPromotionDAOImpl.update(promotionDate);
	}

	@Override
	public boolean deletePromotionDateItem(int id) {
		return hotelPromotionDAOImpl.deleteDatesPromotion(id);
	}

	@Override
	public boolean addPromotionDateItem(PromotionDate promotionDate) {
		return hotelPromotionDAOImpl.add(promotionDate);
	}

	@Override
	public boolean addHotelPromotionItem(HotelPromotionItem hotelPromotionItem) {
		return hotelPromotionDAOImpl.add(hotelPromotionItem);
	}

	@Override
	public boolean updateHotelPromotionItem(HotelPromotionItem hotelPromotionItem) {
		return hotelPromotionDAOImpl.update(hotelPromotionItem);
	}

	@Override
	public HotelPromotionItem getHotelPromotionItem(int hotelID) {
		return hotelPromotionDAOImpl.getHotelPromotionItem(hotelID);
	}
 

}
