package com.echo.domain.po;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Hotel implements Serializable{

	private static final long serialVersionUID = 1429387723728928010L;
	private int hotelID;    //酒店ID
	private String hotelName;  //酒店名称
	private String city;       //所在城市
	private String district;   //所属商圈
	private String address;     //地址
	private String briefIntro;  //简介
	private String facility;     //设施服务
	private byte starLevel;     //星级
	
	public Hotel(){}
	
	public Hotel(int hotelID, String hotelName, String city, String district, byte starLevel) {
		super();
		this.hotelID = hotelID;
		this.hotelName = hotelName;
		this.city = city;
		this.district = district;
		this.starLevel = starLevel;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getHotelID() {
		return hotelID;
	}
	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBriefIntro() {
		return briefIntro;
	}
	public void setBriefIntro(String briefIntro) {
		this.briefIntro = briefIntro;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public byte getStarLevel() {
		return starLevel;
	}
	public void setStarLevel(byte starLevel) {
		this.starLevel = starLevel;
	}
	@Override
	public String toString() {
		return "Hotel [hotelID=" + hotelID + ", hotelName=" + hotelName + ", district=" + district + ", address="
				+ address + ", briefIntro=" + briefIntro + ", facility=" + facility + ", starLevel=" + starLevel + "]";
	}
	

}
