package com.echo.domain.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 用于存储信用的相关记录
 */
public class CreditChangeItem implements Serializable{
	
	private static final long serialVersionUID = 7362534580763224923L;
	
	private int itemID;         //充值记录的ID
	private int customerID;     //用户ID
	private int orderID;        //相关订单ID
	private byte operationType;  //操作分类（订单执行、订单异常、订单撤销、充值）  
	private int hotelID;         //酒店ID
	private String hotelName;    //酒店名称
	private double amount;      //信用值的增减值
	private Date changeDate;    //信用值改变日期
	
	public CreditChangeItem(){}
	
	public CreditChangeItem(int customerID, int orderID, byte operationType, int hotelID, String hotelName,
			double amount) {
		super();
		this.customerID = customerID;
		this.orderID = orderID;
		this.operationType = operationType;
		this.hotelID = hotelID;
		this.hotelName = hotelName;
		this.amount = amount;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public byte getOperationType() {
		return operationType;
	}
	public void setOperationType(byte operationType) {
		this.operationType = operationType;
	}
	public int getHotelID() {
		return hotelID;
	}
	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	
	 
}
