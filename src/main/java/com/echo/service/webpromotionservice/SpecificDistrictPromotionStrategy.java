package com.echo.service.webpromotionservice;

import com.echo.domain.po.DistrictDiscount;
import com.echo.domain.type.MemberLevelType;

public class SpecificDistrictPromotionStrategy implements WebPromotionStrategy {

	@Override
	public double getDiscount(WebPromotionHandleContext ctx) {
		
		byte grade = ctx.getWebPromotionParameters().getGrade();
		
			DistrictDiscount  districtDiscount =ctx.getDistrictDiscount();
			//无该商圈的优惠
			if(districtDiscount == null){
				return 1;
			}
			
			//特定商圈折扣中VIP还是使用普通会员的等级，不过折扣不一样。
			byte [] gradeArray = new byte[]{
					MemberLevelType.Level1,
					MemberLevelType.Level2,
					MemberLevelType.Level3,
					MemberLevelType.Level4,
					MemberLevelType.Level5
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
				if(grade == gradeArray[i]){
					discount = discountArray[i];
					break;
				}
			}
			
			return discount;
		
	}

}
