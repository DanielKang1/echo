package com.echo.service.hotelpromotionservice;

import com.echo.domain.po.HotelPromotionItem;

public class BookingNumPromotionStrategy implements HotelPromotionStrategy{
	

	@Override
	public double getPrice(HotelPriceHandleContext ctx) {
		
		HotelPromotionItem  item = ctx.getHotelPromotionItem();
		double originalPrice = ctx.getHotelPromotionParameters().getOriginalPrice();
		
		if(item == null || item.getBookingDiscountSwitch() == false){
			return originalPrice;
		}
		
		int bookingMeasure = item.getBookingMeasure();
		if(ctx.getHotelPromotionParameters().getBookingNum() >= bookingMeasure){
			return originalPrice * item.getBookingDiscount();
		}
		return originalPrice;
	}

}
