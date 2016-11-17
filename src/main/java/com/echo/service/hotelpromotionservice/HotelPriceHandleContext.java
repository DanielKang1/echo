package com.echo.service.hotelpromotionservice;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.echo.domain.po.HotelPromotionItem;

public class HotelPriceHandleContext {
	
	@Autowired
	private HotelPromotionServiceImpl hotelPromotionServiceImpl;
	
	private int hotelID; //酒店ID
	private double originalPrice;  //原先价格
	private Date birthday;  //生日
	private int bookingNum;  //预订数量
	private byte grade;   //用户等级
	private HotelPromotionItem  hotelPromotionItem; 
	
	public HotelPriceHandleContext(int hotelID, double originalPrice, Date birthday, int bookingNum, byte grade) {
		this.hotelID = hotelID;
		this.originalPrice = originalPrice;
		this.birthday = birthday;
		this.bookingNum = bookingNum;
		this.grade = grade;
		this.hotelPromotionItem = hotelPromotionServiceImpl.getHotelPromotionItem(hotelID);
	}
	
	public int getHotelID() {
		return hotelID;
	}

	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}


	public double getOriginalPrice() {
		return originalPrice;
	}


	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getBookingNum() {
		return bookingNum;
	}

	public void setBookingNum(int bookingNum) {
		this.bookingNum = bookingNum;
	}

	public byte getGrade() {
		return grade;
	}

	public void setGrade(byte grade) {
		this.grade = grade;
	}
	
	public HotelPromotionItem getHotelPromotionItem() {
		return hotelPromotionItem;
	}

	public void setHotelPromotionItem(HotelPromotionItem hotelPromotionItem) {
		this.hotelPromotionItem = hotelPromotionItem;
	}
 



	/**
	 * 使用策略模式
	 * 可以获得四种策略的价格，具体如何安排可以另行商议
	 * 暂且使用4种策略的最小值。
	 */
	public double getResult(){
		
		HotelPromotionStrategy  birthdayStrategy = new BirthdayPromotionStrategy();
		double p1 = birthdayStrategy.getPrice(this);
		HotelPromotionStrategy  bookingNumStrategy = new BookingNumPromotionStrategy();
		double p2 = bookingNumStrategy.getPrice(this);
		HotelPromotionStrategy  cooperativeEnterpriseStrategy = new CooperativeEnterprisePromotionStrategy();
		double p3 = cooperativeEnterpriseStrategy.getPrice(this);
		HotelPromotionStrategy  specificDateStrategy = new SpecificDatePromotionStrategy();
		double p4 = specificDateStrategy.getPrice(this);
		
		double min = (p1 > p2) ? p2 : p1;
		min = (min > p3 ) ? p3 : min;
		min = (min > p4 ) ? p4 : min;
		
		return min;
	}
	
	

}
