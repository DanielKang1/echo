package com.echo.domain.po;

import java.io.Serializable;

public class Room implements Serializable{

	private static final long serialVersionUID = -232997538162339408L;
	
	private int id;  
	private int hotelID; //所属酒店ID
	private int roomNumber;  //在酒店中的房间号码   
	private int typeID;  //客房类型ID
	private String typeName;  //客房类型名称
	private double price;  //价格
	private byte status;  //客房状态
	
	public Room(){}
	
	public Room(int hotelID, int roomNumber, int typeID, String typeName, double price, byte status) {
		super();
		this.hotelID = hotelID;
		this.roomNumber = roomNumber;
		this.typeID = typeID;
		this.typeName = typeName;
		this.price = price;
		this.status = status;
	}
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
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", hotelID=" + hotelID + ", roomNumber=" + roomNumber + ", typeID=" + typeID
				+ ", typeName=" + typeName + ", price=" + price + ", status=" + status + "]";
	}
	
	 
}
