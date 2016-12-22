package com.echo.service.hotelpromotionservice;

import com.echo.domain.po.HotelPromotionItem;
import com.echo.domain.type.MemberLevelType;

public class CooperativeEnterprisePromotionStrategy implements HotelPromotionStrategy{
	

	@Override
	public double getPrice(HotelPriceHandleContext ctx) {
		HotelPromotionItem  item = ctx.getHotelPromotionItem();
		double originalPrice = ctx.getHotelPromotionParameters().getOriginalPrice();
		
		if(item == null || item.getCooperativeEnterpriseSwitch() == false){
			return originalPrice;
		}
		
		if(ctx.getCompanyMember() != null){
			return originalPrice * item.getCooperativeEnterpriseDiscount();
		}
		
		return originalPrice;
	}

}
