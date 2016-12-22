package com.echo.service.webpromotionservice;

public class WebPromotionParameters {
	
	private byte grade; //用户等级
	private String district; //所属地区
	
	public WebPromotionParameters(byte grade, String district) {
		this.grade = grade;
		this.district = district;
	}
	public byte getGrade() {
		return grade;
	}
	public void setGrade(byte grade) {
		this.grade = grade;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
}
