package com.echo.domain.po;

import java.io.Serializable;

/**
 * 存放Customer和Enterprise的关联关系。即该Customer为Enterprise的员工。
 */
public class CusEnterItem implements Serializable{
	
	private static final long serialVersionUID = -7008210265862055845L;
	
	private int itemID;
	private int CustomerID;
	private int EnterpriseID;
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public int getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}
	public int getEnterpriseID() {
		return EnterpriseID;
	}
	public void setEnterpriseID(int enterpriseID) {
		EnterpriseID = enterpriseID;
	}
	@Override
	public String toString() {
		return "CusEnterItem [itemID=" + itemID + ", CustomerID=" + CustomerID + ", EnterpriseID=" + EnterpriseID + "]";
	}
	

}
