package com.echo.domain.po;

import java.io.Serializable;
import java.util.Date;
/**
 * 存储入住处理和退房处理所需要用到的持久层对象
 */
public class RoomCheckItem implements Serializable{
	
	private static final long serialVersionUID = -2679477709957308574L;
	
	private int id;
	private int hotelID;  //酒店ID
	private int userID;   //用户ID
	private int roomNumber;   //房间号
	private int orderID;   //订单号
	private Date checkInDate;   //入住时间
	private Date checkOutDate;   //退房时间
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
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	@Override
	public String toString() {
		return "RoomCheckItem [id=" + id + ", hotelID=" + hotelID + ", userID=" + userID + ", roomNumber=" + roomNumber
				+ ", orderID=" + orderID + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + "]";
	}
	
 
	
}
