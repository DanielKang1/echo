package com.echo.domain.vo;

import java.io.Serializable;

public class HotelSearcher implements Serializable{
	
	private static final long serialVersionUID = 5880019495796864441L;
	
	private String city;   //城市
	private String district; //商圈
	private byte starLevel;  //星级
	private String keyWord;   //关键词（补充搜索 酒店名， 酒店介绍，设施服务）
	private double priceFloor;  //价格下限
	private double priceCeiling; //价格上限
	
	public HotelSearcher(){}
	
	public HotelSearcher(String city, String district, byte starLevel, String keyWord, double priceFloor,double priceCeiling) {
		super();
		this.city = city;
		this.district = district;
		this.starLevel = starLevel;
		this.keyWord = keyWord;
		this.priceCeiling = priceCeiling;
		this.priceFloor = priceFloor;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public byte getStarLevel() {
		return starLevel;
	}
	public void setStarLevel(byte starLevel) {
		this.starLevel = starLevel;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public double getPriceCeiling() {
		return priceCeiling;
	}
	public void setPriceCeiling(double priceCeiling) {
		this.priceCeiling = priceCeiling;
	}
	public double getPriceFloor() {
		return priceFloor;
	}
	public void setPriceFloor(double priceFloor) {
		this.priceFloor = priceFloor;
	}

	@Override
	public String toString() {
		return "HotelSearcher [city=" + city + ", district=" + district + ", starLevel=" + starLevel + ", keyWord="
				+ keyWord + ", priceFloor=" + priceFloor + ", priceCeiling=" + priceCeiling + "]";
	}
	
	

	

}
