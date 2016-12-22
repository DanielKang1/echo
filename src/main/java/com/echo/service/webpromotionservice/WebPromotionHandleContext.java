package com.echo.service.webpromotionservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.echo.domain.po.DistrictDiscount;
import com.echo.domain.po.MemberDiscount;
import com.echo.domain.po.PromotionDate;

@Component
public class WebPromotionHandleContext {
	
 
	private WebPromotionParameters webPromotionParameters;
	
	private MemberDiscount  memberDiscount;
	
	private DistrictDiscount  districtDiscount;
	
	private List<PromotionDate> promotionDates;
	
	@Autowired
	private WebPromotionServiceImpl webPromotionServiceImpl;
	
	
	//获取最终结果（平台提供的折扣率）
	public double getFinalDiscount(){
		
		//会员（普通会员及VIP会员）等级折扣
		memberDiscount = webPromotionServiceImpl.getMemberDiscountByLevelID(webPromotionParameters.getGrade());
		WebPromotionStrategy memberLevelStrategy = new MemberLevelPromotionStrategy();
		double p1 = memberLevelStrategy.getDiscount(this);
	 
		//VIP在特定商圈享受折扣
		districtDiscount = webPromotionServiceImpl.getDistrictDiscountByName(webPromotionParameters.getDistrict());
		WebPromotionStrategy specificDistrictStrategy = new SpecificDistrictPromotionStrategy();
		double p2 = specificDistrictStrategy.getDiscount(this);
	 
		//特定日期折扣
		promotionDates = webPromotionServiceImpl.getWebPromotionDateList();
		WebPromotionStrategy specificDateStrategy = new SpecificDatePromotionStrategy();
		double p3 = specificDateStrategy.getDiscount(this);
	 
		//会员等级与特定商圈优惠只能二选一，特定商圈优惠优先级高
		if(p2 != 1){
			return p2 * p3;
		}
		else{
			return p1 * p3;
		}
		 
	}


	public WebPromotionParameters getWebPromotionParameters() {
		return webPromotionParameters;
	}
	
	
	public void setWebPromotionParameters(WebPromotionParameters webPromotionParameters) {
		this.webPromotionParameters = webPromotionParameters;
	}

	public MemberDiscount getMemberDiscount() {
		return memberDiscount;
	}


	public DistrictDiscount getDistrictDiscount() {
		return districtDiscount;
	}


	public List<PromotionDate> getPromotionDates() {
		return promotionDates;
	}
	
	

}
