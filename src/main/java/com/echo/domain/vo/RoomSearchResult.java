package com.echo.domain.vo;

import java.io.Serializable;

public class RoomSearchResult implements Serializable{
	
	private static final long serialVersionUID = -4563662883966974545L;
	
	private int roomTypeID;       //房间类型ID
	private String roomTypeName;   //房间类型名称
	private double price;         //价格
	private int roomNum;        //该类房间数量
	
	public RoomSearchResult(){}

	public int getRoomTypeID() {
		return roomTypeID;
	}

	public void setRoomTypeID(int roomTypeID) {
		this.roomTypeID = roomTypeID;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public RoomSearchResult(int roomTypeID, String roomTypeName, double price, int roomNum) {
		super();
		this.roomTypeID = roomTypeID;
		this.roomTypeName = roomTypeName;
		this.price = price;
		this.roomNum = roomNum;
	}

	@Override
	public String toString() {
		return "RoomSearchResult [roomTypeID=" + roomTypeID + ", roomTypeName=" + roomTypeName + ", price=" + price
				+ ", roomNum=" + roomNum + "]";
	}
	
	

}
