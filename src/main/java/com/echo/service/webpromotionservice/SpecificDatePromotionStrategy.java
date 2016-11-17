package com.echo.service.webpromotionservice;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.echo.domain.po.PromotionDate;


public class SpecificDatePromotionStrategy implements WebPromotionStrategy {
	
	@Autowired
	private WebPromotionServiceImpl webPromotionServiceImpl;

	@Override
	public double getDiscount(WebPromotionHandleContext ctx) {
		
		List<PromotionDate> dates = webPromotionServiceImpl.getWebPromotionDateList();
		Date current = new Date();
		double discount = 1;
		//判断当天是否是促销日期
		for(PromotionDate pd : dates){
			try {
				if( getDiff(current,pd.getStartDate()) >= 0 && getDiff(pd.getEndDate(),current) >= 0){
					discount = pd.getDiscount();
					break;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return discount;
	}
	
	private static long getDiff(Date date1,Date date2) throws ParseException{
		  long quot=0;
		  quot=date1.getTime()-date2.getTime();
		  quot=quot/1000/60/60/24;
		  return quot;
	  }

 

}
