package com.echo.domain.po;

//登记合作企业信息
public class CompanyMember {
	
	private int itemID;  
	private int customerID;
	private String companyName;  //合作企业名称
	
	public CompanyMember(){}
	
	public CompanyMember(int customerID, String companyName) {
		this.customerID = customerID;
		this.companyName = companyName;
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Override
	public String toString() {
		return "CompanyMember [itemID=" + itemID + ", customerID=" + customerID + ", companyName=" + companyName + "]";
	}
	

}
