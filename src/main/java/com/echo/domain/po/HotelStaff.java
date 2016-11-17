package com.echo.domain.po;

import java.io.Serializable;

public class HotelStaff  implements Serializable{
	
	private static final long serialVersionUID = 2211601928054934294L;
	
	private int staffID;    //员工ID
	private String staffName;   //员工姓名
	private int hotelID;   //酒店ID
	private String pwdsalt;   //酒店工作人员密码的盐
	private String pwd;   //酒店工作人员密码
	private String phone;   //手机号
	
	public int getStaffID() {
		return staffID;
	}
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public int getHotelID() {
		return hotelID;
	}
	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}
	public String getPwdsalt() {
		return pwdsalt;
	}
	public void setPwdsalt(String pwdsalt) {
		this.pwdsalt = pwdsalt;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "HotelStaff [staffID=" + staffID + ", staffName=" + staffName + ", hotelID=" + hotelID + ", pwdsalt="
				+ pwdsalt + ", pwd=" + pwd + ", phone=" + phone + "]";
	}
	
	

}
