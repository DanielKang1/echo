package com.echo.domain.vo;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotEmpty;

public class Login implements Serializable{

	private static final long serialVersionUID = 6500597133590910364L;
	
	@NotEmpty
	private String uservalue;
	@NotEmpty
	private String pwd;
	@NotEmpty
	private String captcha;
	
	
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getUservalue() {
		return uservalue;
	}
	public void setUservalue(String uservalue) {
		this.uservalue = uservalue;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "Login [uservalue=" + uservalue + ", pwd=" + pwd + ", captcha=" + captcha + "]";
	}
	
	
}
