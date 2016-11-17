package com.echo.service.webpromotionservice;

import org.springframework.beans.factory.annotation.Autowired;

import com.echo.dao.webpromotiondao.WebPromotionDAOImpl;
import com.echo.domain.po.DistrictDiscount;
import com.echo.domain.type.MemberLevelType;

public class SpecificDistrictPromotionStrategy implements WebPromotionStrategy {

	@Autowired
	private WebPromotionServiceImpl webPromotionServiceImpl;
	
	@Override
	public double getDiscount(WebPromotionHandleContext ctx) {
		
		//前提：你是VIP会员
		if(ctx.getGrade() >= MemberLevelType.VIP1 && ctx.getGrade() <= MemberLevelType.VIP5){
			DistrictDiscount  districtDiscount = webPromotionServiceImpl.getDistrictDiscountByName(ctx.getDistrict());
			//无该商圈的优惠
			if(districtDiscount == null){
				return 1;
			}
			
			//特定商圈折扣中VIP还是使用普通会员的等级，不过折扣不一样。
			byte [] gradeArray = new byte[]{
					MemberLevelType.VIP1,
					MemberLevelType.VIP2,
					MemberLevelType.VIP3,
					MemberLevelType.VIP4,
					MemberLevelType.VIP5
					};
			double [] discountArray = new double[]{
					districtDiscount.getV1Discount(), //普通会员1级的用户，在其成为VIP1后，该用户在商圈的折扣
					districtDiscount.getV2Discount(),
					districtDiscount.getV3Discount(),
					districtDiscount.getV4Discount(),
					districtDiscount.getV5Discount()
					};
			
			double discount = 1;
			
			for(int i = 0; i < gradeArray.length ; i++){
				if(ctx.getGrade() == gradeArray[i]){
					discount = discountArray[i];
					break;
				}
			}
			
			return discount;
		}
		
		return 1;
	}

}
