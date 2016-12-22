package com.echo.service.hotelpromotionservice;

import java.util.Date;

public class HotelPromotionParameters {
	
	private int hotelID; //酒店ID
	private double originalPrice;  //原先价格
	private Date birthday;  //生日
	private int bookingNum;  //预订数量
	private int customerID;
	
	public HotelPromotionParameters(int hotelID, double originalPrice, Date birthday, int bookingNum, int customerID) {
		this.hotelID = hotelID;
		this.originalPrice = originalPrice;
		this.birthday = birthday;
		this.bookingNum = bookingNum;
		this.customerID = customerID;
	}
	
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
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
	
	

}
