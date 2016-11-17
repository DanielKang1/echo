package com.echo.service.hotelpromotionservice;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.echo.domain.po.HotelPromotionItem;

public class BirthdayPromotionStrategy implements HotelPromotionStrategy{
	
	@Override
	public double getPrice(HotelPriceHandleContext ctx) {
		HotelPromotionItem  item = ctx.getHotelPromotionItem();
		if(item == null || item.getBirthdaySwitch() == false){
			return ctx.getOriginalPrice();
		}
		
		if(allowable(ctx.getBirthday())){
			return item.getBirthdayDiscount() * ctx.getOriginalPrice();
		}
		
		return ctx.getOriginalPrice();
	}
	
	//查看date的月与日是否与今天相同
	private boolean allowable(Date date){
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(currentDate);
		int month1 = cal.get(Calendar.MONTH);
		int day1 = cal.get(Calendar.DAY_OF_MONTH);
		
		cal.setTime(date);
		int month2 = cal.get(Calendar.MONTH);
		int day2 = cal.get(Calendar.DAY_OF_MONTH);
		
		return (month1 == month2) && (day1 == day2);
		
	}

}
