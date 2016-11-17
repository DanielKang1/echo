package com.echo.domain.po;

import java.io.Serializable;
import java.util.Date;

public class Evaluation implements Serializable{
	
	private static final long serialVersionUID = -6420571709225024923L;
	
	private int EvalutionID;      //评论ID
	private int customerID;       //客户ID
	private String customerName;   //客户姓名
	private int hotelID;            //酒店ID
	private String RoomTypeName;   //房间类型
	private double mark;          //评分
	private String merit;          //优点
	private String demerit;        //缺点
	private String comment;        //评论内容
	private Date releaseTime;       //发布时间
	
	
	public String getMerit() {
		return merit;
	}
	public void setMerit(String merit) {
		this.merit = merit;
	}
	public String getDemerit() {
		return demerit;
	}
	public void setDemerit(String demerit) {
		this.demerit = demerit;
	}
	public int getEvalutionID() {
		return EvalutionID;
	}
	public void setEvalutionID(int evalutionID) {
		EvalutionID = evalutionID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getHotelID() {
		return hotelID;
	}
	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}
	public String getRoomTypeName() {
		return RoomTypeName;
	}
	public void setRoomTypeName(String roomTypeName) {
		RoomTypeName = roomTypeName;
	}
	public double getMark() {
		return mark;
	}
	public void setMark(double mark) {
		this.mark = mark;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	@Override
	public String toString() {
		return "Evaluation [EvalutionID=" + EvalutionID + ", customerID=" + customerID + ", customerName="
				+ customerName + ", hotelID=" + hotelID + ", RoomTypeName=" + RoomTypeName + ", mark=" + mark
				+ ", merit=" + merit + ", demerit=" + demerit + ", comment=" + comment + ", releaseTime=" + releaseTime
				+ "]";
	}
 
}
