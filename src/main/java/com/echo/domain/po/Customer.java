package com.echo.domain.po;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class Customer implements Serializable {

	private static final long serialVersionUID = 3868239518492044085L;
	
	private int customer_id; 
	@NotEmpty
	private String nickname;
/*
 *   注：在此项目不能用@Email 
 *   因为使用Hibernate时，数据库中数据和domain对象绑定，若要加密存储email，只能customer.setEmail(密文)，
 *   若email强制为@Email了，setEmail(密文)会报错。所以还是自己手动写验证。
 */
	@NotEmpty
	private String email;
	private String pwdsalt;    	   //密码加盐
	@NotEmpty
	private String pwd;   	      
	@NotEmpty
	private String phone;     
	private double credit ;     //信用值
	private byte grade ;     //用户等级
	@Past
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;        //用户生日
	
	public Customer(){
		credit = 100;
		grade = 0;
	}
	
	public Customer(String nickname, String email, String pwd, String phone,double credit) {
		super();
		this.nickname = nickname;
		this.email = email;
		this.pwd = pwd;
		this.phone = phone;
		this.credit = credit;
		grade = 0;
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
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public byte getGrade() {
		return grade;
	}
	public void setGrade(byte grade) {
		this.grade = grade;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", nickname=" + nickname + ", email=" + email + ", pwdsalt="
				+ pwdsalt + ", pwd=" + pwd + ", phone=" + phone + ", credit=" + credit + ", grade=" + grade
				+ ", birthday=" + birthday + "]";
	}
	

}
