package com.echo.service.hotelpromotionservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.echo.domain.po.CompanyMember;
import com.echo.domain.po.HotelPromotionItem;
import com.echo.domain.po.PromotionDate;
import com.echo.service.customerservice.CustomerServiceImpl;

@Component
public class HotelPriceHandleContext {
	
	@Autowired
	private HotelPromotionServiceImpl hotelPromotionServiceImpl;
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	private HotelPromotionParameters hotelPromotionParameters;
 
	private HotelPromotionItem  hotelPromotionItem; 
	
	private List<PromotionDate> promotionDates;
	
	private CompanyMember companyMember;
	
	

	/**
	 * 使用策略模式
	 * 可以获得四种策略的价格，具体如何安排可以另行商议
	 * 暂且使用4种策略的最小值。
	 */
	public double getResult(){
		hotelPromotionItem = hotelPromotionServiceImpl.getHotelPromotionItem(hotelPromotionParameters.getHotelID());	
		promotionDates = hotelPromotionServiceImpl.getHotelPromotionDateList(hotelPromotionParameters.getHotelID());
		
		HotelPromotionStrategy  birthdayStrategy = new BirthdayPromotionStrategy();
		double p1 = birthdayStrategy.getPrice(this);
		System.out.println("BirthdayPromotionStrategy:"+p1);
		
		HotelPromotionStrategy  bookingNumStrategy = new BookingNumPromotionStrategy();
		double p2 = bookingNumStrategy.getPrice(this);
		System.out.println("BookingNumPromotionStrategy:"+p2);
		
		HotelPromotionStrategy  cooperativeEnterpriseStrategy = new CooperativeEnterprisePromotionStrategy();
		companyMember = customerServiceImpl.getCompanyMemberByCID(hotelPromotionParameters.getCustomerID());
		double p3 = cooperativeEnterpriseStrategy.getPrice(this);
		System.out.println("CooperativeEnterprisePromotionStrategy:"+p3);
		
		HotelPromotionStrategy  specificDateStrategy = new SpecificDatePromotionStrategy();
		double p4 = specificDateStrategy.getPrice(this);
		System.out.println("SpecificDatePromotionStrategy:"+p4);
		double min = (p1 > p2) ? p2 : p1;
		min = (min > p3 ) ? p3 : min;
		min = (min > p4 ) ? p4 : min;
		
		return min;
	}
	

	public HotelPromotionParameters getHotelPromotionParameters() {
		return hotelPromotionParameters;
	}



	public void setHotelPromotionParameters(HotelPromotionParameters hotelPromotionParameters) {
		this.hotelPromotionParameters = hotelPromotionParameters;
	}


	public HotelPromotionItem getHotelPromotionItem() {
		return hotelPromotionItem;
	}


	public List<PromotionDate> getPromotionDates() {
		return promotionDates;
	}


	public CompanyMember getCompanyMember() {
		return companyMember;
	}
	
}
