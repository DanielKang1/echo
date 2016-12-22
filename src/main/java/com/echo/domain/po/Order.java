package com.echo.domain.po;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
	
	private static final long serialVersionUID = -1129058439731895386L;
	
	private int orderID;     //订单ID
	private byte orderType;    //订单类型  参照OrderStatusType
	private Hotel hotel;   //相关酒店
	private int customerID;     //客户ID
	private String reservedName;  //预留姓名
	private String reservedPhone;  //预留号码
	private Date creationDate;    //下单时间
	private Date checkinDate;    //入住时间
	private Date checkoutDate;   //退房时间
	private Date latestDate;   //最晚订单执行时间
	private RoomType roomType; //房间类型
	private int bookingNum;    //房间数量
	private double total;   //总额
	private int peopleNum;   //预计入住人数
	private byte hasChild; //有无儿童
	//数据库中还需放入一个日期字段存储每次修改的时间（MySQL自动实现），便可记录下订单状态修改的时间
	
	
	public Order(){}
	
	public Order(byte orderType, Hotel hotel, int customerID, String reservedName, String reservedPhone,
			Date creationDate, Date checkinDate, Date checkoutDate, Date latestDate, RoomType roomType, int bookingNum,
			int peopleNum, byte hasChild) {
		this.orderType = orderType;
		this.hotel = hotel;
		this.customerID = customerID;
		this.reservedName = reservedName;
		this.reservedPhone = reservedPhone;
		this.creationDate = creationDate;
		this.checkinDate = checkinDate;
		this.checkoutDate = checkoutDate;
		this.latestDate = latestDate;
		this.roomType = roomType;
		this.bookingNum = bookingNum;
		this.peopleNum = peopleNum;
		this.hasChild = hasChild;
		//单独留出Price
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public byte getOrderType() {
		return orderType;
	}
	public void setOrderType(byte orderType) {
		this.orderType = orderType;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getReservedName() {
		return reservedName;
	}
	public void setReservedName(String reservedName) {
		this.reservedName = reservedName;
	}
	public String getReservedPhone() {
		return reservedPhone;
	}
	public void setReservedPhone(String reservedPhone) {
		this.reservedPhone = reservedPhone;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}
	public Date getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	public Date getLatestDate() {
		return latestDate;
	}
	public void setLatestDate(Date latestDate) {
		this.latestDate = latestDate;
	}
	public RoomType getRoomType() {
		return roomType;
	}
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	public int getBookingNum() {
		return bookingNum;
	}
	public void setBookingNum(int bookingNum) {
		this.bookingNum = bookingNum;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getPeopleNum() {
		return peopleNum;
	}
	public void setPeopleNum(int peopleNum) {
		this.peopleNum = peopleNum;
	}
	public byte getHasChild() {
		return hasChild;
	}
	public void setHasChild(byte hasChild) {
		this.hasChild = hasChild;
	}
	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", orderType=" + orderType + ", hotel=" + hotel + ", customerID="
				+ customerID + ", reservedName=" + reservedName + ", reservedPhone=" + reservedPhone + ", roomType="
				+ roomType + ", total=" + total + "]";
	}
	 

}
