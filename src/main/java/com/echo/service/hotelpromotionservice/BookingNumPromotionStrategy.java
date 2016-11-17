package com.echo.service.hotelpromotionservice;

import com.echo.domain.po.HotelPromotionItem;

public class BookingNumPromotionStrategy implements HotelPromotionStrategy{
	

	@Override
	public double getPrice(HotelPriceHandleContext ctx) {
		HotelPromotionItem  item = ctx.getHotelPromotionItem();
		if(item == null || item.getBookingDiscountSwitch() == false){
			return ctx.getOriginalPrice();
		}
		
		int bookingMeasure = item.getBookingMeasure();
		if(ctx.getBookingNum() > bookingMeasure){
			return ctx.getOriginalPrice() * item.getBookingDiscount();
		}
		return ctx.getOriginalPrice();
	}

}
