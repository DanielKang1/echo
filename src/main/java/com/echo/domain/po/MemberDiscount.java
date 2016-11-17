package com.echo.domain.po;

import java.io.Serializable;

public class MemberDiscount implements Serializable{
	
	private static final long serialVersionUID = 3656961421569851313L;
	
	private int levelID;
	private String levelName;
	private double discount;
	private double creditLimit;
	
	public int getLevelID() {
		return levelID;
	}
	public void setLevelID(int levelID) {
		this.levelID = levelID;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}
	@Override
	public String toString() {
		return "MemberDiscount [levelID=" + levelID + ", levelName=" + levelName + ", discount=" + discount
				+ ", creditLimit=" + creditLimit + "]";
	}
	
	

}
