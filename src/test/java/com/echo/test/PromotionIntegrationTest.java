package com.echo.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.echo.domain.type.MemberLevelType;
import com.echo.service.hotelpromotionservice.HotelPriceHandleContext;
import com.echo.service.hotelpromotionservice.HotelPromotionParameters;
import com.echo.service.webpromotionservice.WebPromotionHandleContext;
import com.echo.service.webpromotionservice.WebPromotionParameters;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "WebContent")
@ContextHierarchy({
        @ContextConfiguration( locations = "classpath:applicationContext.xml")
})
public class PromotionIntegrationTest {
	
	@Autowired
	private HotelPriceHandleContext hotelPriceHandleContext;
	
	@Autowired
	private WebPromotionHandleContext webPromotionHandleContext;
	
	@Test
	public void testHotelPromotion() throws ParseException{
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse("1994-12-22");
		HotelPromotionParameters paras = new HotelPromotionParameters(1, 1000, date, 3,3);
		hotelPriceHandleContext.setHotelPromotionParameters(paras);
		double result = hotelPriceHandleContext.getResult();
		System.out.println(result);
	}
	
	@Test
	public void testWebPromotion(){
		WebPromotionParameters paras =  new WebPromotionParameters(MemberLevelType.Level2, "新街口地区（市中心）");
		webPromotionHandleContext.setWebPromotionParameters(paras);
		double result = webPromotionHandleContext.getFinalDiscount();
		System.out.println(result);
	}

}
