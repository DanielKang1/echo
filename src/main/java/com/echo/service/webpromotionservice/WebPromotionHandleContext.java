package com.echo.service.webpromotionservice;

public class WebPromotionHandleContext {
	
	private byte grade; //用户等级
	private String district; //所属地区
	
	public WebPromotionHandleContext(byte grade,String district) {
		this.grade = grade;
		this.district = district;
	}
	public byte getGrade() {
		return grade;
	}
	
	public String getDistrict(){
		return district;
	}
	
	//获取最终结果（平台提供的折扣率）
	public double getFinalDiscount(){
		//会员（普通会员及VIP会员）等级折扣
		WebPromotionStrategy memberLevelStrategy = new MemberLevelPromotionStrategy();
		double p1 = memberLevelStrategy.getDiscount(this);
		
		//VIP在特定商圈享受折扣
		WebPromotionStrategy specificDistrictStrategy = new SpecificDistrictPromotionStrategy();
		double p2 = specificDistrictStrategy.getDiscount(this);
		
		//特定日期折扣
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
	
	

}
