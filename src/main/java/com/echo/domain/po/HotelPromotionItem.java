package com.echo.domain.po;

import java.io.Serializable;

public class HotelPromotionItem implements Serializable{
	
	private static final long serialVersionUID = -3433277683762168199L;
	
	private int id;
	private int hotelID;            //指定优惠策略的酒店ID
	private double birthdayDiscount;  //生日策略折扣
	private boolean birthdaySwitch;    //生日策略开关
	private int bookingMeasure;       //特定数量优惠的数量标准
	private double bookingDiscount;    //特定数量优惠的折扣
	private boolean bookingDiscountSwitch;    //特定数量策略开关
	private double cooperativeEnterpriseDiscount;  //合作企业折扣
	private boolean cooperativeEnterpriseSwitch;    //合作企业折扣策略开关
	//特定日期段折扣 因为在网站营销策略中同样拥有，单独做成了PromotionDate
	
	public HotelPromotionItem(int hotelID, double birthdayDiscount, int bookingMeasure, double bookingDiscount,
			double cooperativeEnterpriseDiscount) {
		this.hotelID = hotelID;
		this.birthdayDiscount = birthdayDiscount;
		this.bookingMeasure = bookingMeasure;
		this.bookingDiscount = bookingDiscount;
		this.cooperativeEnterpriseDiscount = cooperativeEnterpriseDiscount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHotelID() {
		return hotelID;
	}

	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}

	public double getBirthdayDiscount() {
		return birthdayDiscount;
	}

	public void setBirthdayDiscount(double birthdayDiscount) {
		this.birthdayDiscount = birthdayDiscount;
	}

	public boolean getBirthdaySwitch() {
		return birthdaySwitch;
	}

	public void setBirthdaySwitch(boolean birthdaySwitch) {
		this.birthdaySwitch = birthdaySwitch;
	}

	public int getBookingMeasure() {
		return bookingMeasure;
	}

	public void setBookingMeasure(int bookingMeasure) {
		this.bookingMeasure = bookingMeasure;
	}

	public double getBookingDiscount() {
		return bookingDiscount;
	}

	public void setBookingDiscount(double bookingDiscount) {
		this.bookingDiscount = bookingDiscount;
	}

	public boolean getBookingDiscountSwitch() {
		return bookingDiscountSwitch;
	}

	public void setBookingDiscountSwitch(boolean bookingDiscountSwitch) {
		this.bookingDiscountSwitch = bookingDiscountSwitch;
	}

	public double getCooperativeEnterpriseDiscount() {
		return cooperativeEnterpriseDiscount;
	}

	public void setCooperativeEnterpriseDiscount(double cooperativeEnterpriseDiscount) {
		this.cooperativeEnterpriseDiscount = cooperativeEnterpriseDiscount;
	}

	public boolean getCooperativeEnterpriseSwitch() {
		return cooperativeEnterpriseSwitch;
	}

	public void setCooperativeEnterpriseSwitch(boolean cooperativeEnterpriseSwitch) {
		this.cooperativeEnterpriseSwitch = cooperativeEnterpriseSwitch;
	}
	
}
