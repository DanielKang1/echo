package com.echo.service.webpromotionservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.echo.dao.webpromotiondao.WebPromotionDAOImpl;
import com.echo.domain.po.DistrictDiscount;
import com.echo.domain.po.MemberDiscount;
import com.echo.domain.po.PromotionDate;

@Service
public class WebPromotionServiceImpl implements WebPromotionService{
	
	@Autowired
	private WebPromotionDAOImpl webPromotionDAOImpl;
	

	@Override
	public boolean addMemberDiscount(MemberDiscount memberDistrict) {
		return webPromotionDAOImpl.add(memberDistrict);
	}

	@Override
	public boolean updateMemberDiscount(MemberDiscount memberDistrict) {
		return webPromotionDAOImpl.update(memberDistrict);
	}

	@Override
	public boolean deleteMemberDiscount(int levelID) {
		return webPromotionDAOImpl.deleteMemberDiscount(levelID);
	}


	@Override
	public MemberDiscount getMemberDiscountByLevelID(int levelID) {
		return webPromotionDAOImpl.getMemberDiscount(levelID);
	}
	
	@Override
	public boolean addDistrictDiscount(DistrictDiscount districtDiscount) {
		return webPromotionDAOImpl.add(districtDiscount);
	}

	@Override
	public boolean updateDistrictDiscount(DistrictDiscount districtDiscount) {
		return webPromotionDAOImpl.update(districtDiscount);
	}

	@Override
	public boolean deleteDistrictDiscount(int districtDiscountID) {
		return webPromotionDAOImpl.deleteDistrictDiscount(districtDiscountID);
	}

	@Override
	public DistrictDiscount getDistrictDiscountByName(String districtName) {
		return webPromotionDAOImpl.getDistrictDiscount(districtName);
	}

	@Override
	public DistrictDiscount getDistrictDiscountByID(int ID) {
		return webPromotionDAOImpl.getDistrictDiscount(ID);
	}

	@Override
	public List<PromotionDate> getWebPromotionDateList() {
		return webPromotionDAOImpl.getPromotionDateList();
	}

	@Override
	public boolean updateDatePromotionItem(PromotionDate promotionDate) {
		return webPromotionDAOImpl.updateDatesPromotion(promotionDate);
	}

	@Override
	public boolean deleteDatePromotionItem(int id) {
		return webPromotionDAOImpl.deleteDatesPromotion(id);
	}

	@Override
	public boolean addDatePromotionItem(PromotionDate promotionDate) {
		return webPromotionDAOImpl.addPromotionDates(promotionDate);
	}

	

}
