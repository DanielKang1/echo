package com.echo.domain.po;

import java.io.Serializable;

public class DistrictDiscount implements Serializable{

	private static final long serialVersionUID = -3628021094873834630L;
	
	private int id;
	private String cityName;
	private String districtName;
	private double v1Discount;
	private double v2Discount;
	private double v3Discount;
	private double v4Discount;
	private double v5Discount;
	
	public DistrictDiscount(){}

	public DistrictDiscount(String cityName, String districtName, double v1Discount, double v2Discount,
			double v3Discount, double v4Discount, double v5Discount) {
		this.cityName = cityName;
		this.districtName = districtName;
		this.v1Discount = v1Discount;
		this.v2Discount = v2Discount;
		this.v3Discount = v3Discount;
		this.v4Discount = v4Discount;
		this.v5Discount = v5Discount;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public double getV1Discount() {
		return v1Discount;
	}

	public void setV1Discount(double v1Discount) {
		this.v1Discount = v1Discount;
	}

	public double getV2Discount() {
		return v2Discount;
	}

	public void setV2Discount(double v2Discount) {
		this.v2Discount = v2Discount;
	}

	public double getV3Discount() {
		return v3Discount;
	}

	public void setV3Discount(double v3Discount) {
		this.v3Discount = v3Discount;
	}

	public double getV4Discount() {
		return v4Discount;
	}

	public void setV4Discount(double v4Discount) {
		this.v4Discount = v4Discount;
	}

	public double getV5Discount() {
		return v5Discount;
	}

	public void setV5Discount(double v5Discount) {
		this.v5Discount = v5Discount;
	}

	 
}
