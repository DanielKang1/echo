package com.echo.service.webpromotionservice;

import java.util.List;

import com.echo.domain.po.DistrictDiscount;
import com.echo.domain.po.MemberDiscount;
import com.echo.domain.po.PromotionDate;

public interface WebPromotionService {
	
	public boolean addMemberDiscount(MemberDiscount memberDistrict);
	
	public boolean updateMemberDiscount(MemberDiscount memberDistrict);
	
	public boolean deleteMemberDiscount(int levelID);
	
	public MemberDiscount getMemberDiscountByLevelID(int levelID);
	
	
	public boolean addDistrictDiscount(DistrictDiscount districtDiscount);
	
	public boolean updateDistrictDiscount(DistrictDiscount districtDiscount);
	
	public boolean deleteDistrictDiscount(int districtDiscountID);
	
	public DistrictDiscount getDistrictDiscountByName(String districtName);
	
	public DistrictDiscount getDistrictDiscountByID(int levelID);
	
	
	
	
	public List<PromotionDate> getWebPromotionDateList();
	
	public boolean updateDatePromotionItem(PromotionDate promotionDate);
	
	public boolean deleteDatePromotionItem(int id);
	
	public boolean addDatePromotionItem(PromotionDate promotionDate);
	

}
