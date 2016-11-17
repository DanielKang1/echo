package com.echo.service.hotelpromotionservice;

import com.echo.domain.po.HotelPromotionItem;
import com.echo.domain.type.MemberLevelType;

public class CooperativeEnterprisePromotionStrategy implements HotelPromotionStrategy{
	

	@Override
	public double getPrice(HotelPriceHandleContext ctx) {
		
		HotelPromotionItem  item = ctx.getHotelPromotionItem();
		if(item == null || item.getCooperativeEnterpriseSwitch() == false){
			return ctx.getOriginalPrice();
		}
		
		if(ctx.getGrade() == MemberLevelType.COOPERATIVE_ENTERPRISE){
			
			return item.getCooperativeEnterpriseDiscount() * ctx.getOriginalPrice();
		}
		return ctx.getOriginalPrice();
	}

}
