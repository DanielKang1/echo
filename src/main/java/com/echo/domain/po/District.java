package com.echo.domain.po;

import java.io.Serializable;

public class District implements Serializable{
	
	private static final long serialVersionUID = -1283291111016021037L;
	
	private int id;
	private String districtName;
	private String cityName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	@Override
	public String toString() {
		return "District [id=" + id + ", districtName=" + districtName + ", cityName=" + cityName + "]";
	}
	
	
	
	

}
