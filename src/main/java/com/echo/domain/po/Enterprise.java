package com.echo.domain.po;

import java.io.Serializable;
/**
 * 合作企业
 */
public class Enterprise implements Serializable{
	
	private static final long serialVersionUID = 1027764044642220036L;
	
	private int enterpriseID;
	private String enterpriseName;
	public int getEnterpriseID() {
		return enterpriseID;
	}
	public void setEnterpriseID(int enterpriseID) {
		this.enterpriseID = enterpriseID;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	@Override
	public String toString() {
		return "Enterprise [enterpriseID=" + enterpriseID + ", enterpriseName=" + enterpriseName + "]";
	}
	
	

}
