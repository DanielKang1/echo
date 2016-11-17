package com.echo.domain.po;

import java.io.Serializable;

public class WebMarketer implements Serializable {
	
	private static final long serialVersionUID = 4606258136707365181L;
	
	private int marketerID;
	private String marketerName;
	private String pwdsalt;
	private String pwd;
	
	public int getMarketerID() {
		return marketerID;
	}
	public void setMarketerID(int marketerID) {
		this.marketerID = marketerID;
	}
	public String getMarketerName() {
		return marketerName;
	}
	public void setMarketerName(String marketerName) {
		this.marketerName = marketerName;
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
		return "WebMarketer [marketerID=" + marketerID + ", marketerName=" + marketerName + ", pwdsalt=" + pwdsalt
				+ ", pwd=" + pwd + "]";
	}
	
	

}
