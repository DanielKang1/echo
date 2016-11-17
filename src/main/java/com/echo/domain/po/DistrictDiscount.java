package com.echo.domain.po;

import java.io.Serializable;

public class DistrictDiscount implements Serializable{

	private static final long serialVersionUID = 1358177373382775830L;
	
	private int id;
	private int districtID;
	private String districtName;
	private double v1Discount;
	private double v2Discount;
	private double v3Discount;
	private double v4Discount;
	private double v5Discount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDistrictID() {
		return districtID;
	}
	public void setDistrictID(int districtID) {
		this.districtID = districtID;
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
	
	@Override
	public String toString() {
		return "DistrictDiscount [id=" + id + ", districtID=" + districtID + ", districtName=" + districtName
				+ ", v1Discount=" + v1Discount + ", v2Discount=" + v2Discount + ", v3Discount=" + v3Discount
				+ ", v4Discount=" + v4Discount + ", v5Discount=" + v5Discount + "]";
	}
	
	
}
