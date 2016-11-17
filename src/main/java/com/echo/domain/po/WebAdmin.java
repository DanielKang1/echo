package com.echo.domain.po;

import java.io.Serializable;
/**
 * 平台管理员
 */
public class WebAdmin implements Serializable{
	
	private static final long serialVersionUID = -1735044447191164116L;
	
	private int adminID;
	private String adminName;
	private String pwdsalt;
	private String pwd;
	public int getAdminID() {
		return adminID;
	}
	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getPwdsalt() {
		return pwdsalt;
	}
	public void setPwdsalt(String pwdsalt) {
		this.pwdsalt = pwdsalt;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "WebAdmin [adminID=" + adminID + ", adminName=" + adminName + ", pwdsalt=" + pwdsalt + ", pwd=" + pwd
				+ "]";
	}
	

}
