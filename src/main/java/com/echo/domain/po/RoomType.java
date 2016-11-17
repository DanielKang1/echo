package com.echo.domain.po;

import java.io.Serializable;

public class RoomType implements Serializable{
	
	private static final long serialVersionUID = 5561137861331345459L;
	
	private int typeID;
	private int hotelID;
	private double price;
	private String typeName;
	private String typeDesc;
	
	public RoomType(){}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public int getHotelID() {
		return hotelID;
	}
	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		return "RoomType [typeID=" + typeID + ", hotelID=" + hotelID + ", typeName=" + typeName + ", typeDesc="
				+ typeDesc + "]";
	}
 

}
