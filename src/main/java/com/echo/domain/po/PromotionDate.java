package com.echo.domain.po;

import java.io.Serializable;
import java.util.Date;

public class PromotionDate implements Serializable {
	
	private static final long serialVersionUID = -1571837580347210473L;
	
	private int id;
	private int hotelID;  //酒店ID
	private Date startDate;  //促销开始时间
	private Date endDate;  //促销结束时间
	private double discount;  //折扣
	
	public PromotionDate(){}
	
	public PromotionDate(int hotelID, Date startDate, Date endDate, double discount) {
		this.hotelID = hotelID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.discount = discount;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	

}
