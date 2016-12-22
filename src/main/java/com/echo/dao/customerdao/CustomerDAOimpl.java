package com.echo.dao.customerdao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.echo.domain.po.CompanyMember;
import com.echo.domain.po.Customer;
import com.echo.utils.DESUtils;
import com.echo.utils.EncodeUtils;

@Repository
public class CustomerDAOimpl  implements CustomerDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
 
	@Override
	public boolean add(Customer customer) {
		int result = (int) getSession().save(customer);
		//save返回存储对象时生成的主键
		if(result > 0 ){
			return true;
		}
		else{
			return false;
		}
	}
 

	/**
	 * 在注册时：验证手机号、邮箱地址、用户名是否重复
	 * @param field CustomerAttributeType中取值
	 */
	@Override
	public boolean hasSame(String field, String value) {
		String hql = "FROM Customer c WHERE c."+field+" = ?";
		Query query = getSession().createQuery(hql);
		query .setString(0, DESUtils.getEncryptString(value));
		List<Customer> customerList = query.list();
		if(customerList.size() > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 在修改个人信息时：验证手机号、邮箱地址、用户名是否与其他人重复
	 * @param field CustomerAttributeType中取值
	 */
	public boolean hasSame(String field, String value,int id) {
		String hql = "FROM Customer c WHERE c."+field+" = ? AND c.customer_id <> ?";
		Query query = getSession().createQuery(hql);
		query .setString(0, DESUtils.getEncryptString(value)).setInteger(1, id);
		List<Customer> customerList = query.list();
		if(customerList.size() > 0){
			return true;
		}
		return false;
	}

	
	@Override
	public boolean update(Customer customer) {
		try {
			getSession().update(customer);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Customer get(int customer_id) {
		return (Customer)getSession().get(Customer.class, customer_id);
	}
	
	@Override
	public Customer get(String name) {
		String hql = "FROM Customer WHERE nickname = ?";   
		Query query = getSession().createQuery(hql);
		query.setCacheable(true);
		List<Customer> result = query.setString(0,DESUtils.getEncryptString(name)).list();
		Customer customer = null;
		if(result.size() > 0 ){
			customer = result.get(0);
		}
		return customer;
	}

	/**
	 * 用于用户登录：
	 * value为 用户名/邮箱/手机号 中的一种
	 * pwd 为 用户输入的密码 
	 */
	@Override
	public Customer get(String value, String pwd) {
		
		String pwdsalt = getPwdsalt(value);
		//输入的value(email/nickname/phone)是存在的，开始验证密码
		if(pwdsalt != null){
			int result = checkPwd(value, pwdsalt, pwd);
				if(result != 0){
					return get(result);
				}
		}
		
		return null;
	}

	//获取用户的pwdsalt
	private String getPwdsalt(String value) {
		
		String encodeValue = DESUtils.getEncryptString(value);
 		//注：hql是不支持union
		String sql = "SELECT c1.pwdsalt FROM Customer c1 WHERE c1.nickname = ?  "
				   + "UNION ALL "
				   + "SELECT c2.pwdsalt FROM Customer c2 WHERE c2.email = ?  "
				   + "UNION ALL "
				   + "SELECT c3.pwdsalt FROM Customer c3 WHERE c3.phone = ?  ";
		
		Query query = getSession().createSQLQuery(sql);
		List<String> result = query.setString(0,encodeValue).setString(1,encodeValue).setString(2,encodeValue).list();
		String pwdsalt = null;
		if(result.size() > 0){
			 pwdsalt = result.get(0);
		}
		return pwdsalt;
	}
	
	/**
	 * 检查密码是否正确
	 * @param value 用户输入的phone/email/nickname
	 * @param salt 该用户在数据库中的盐
	 * @param pwd 用户输入的密码
	 * @return 返回该用户的ID（若不存在则返回0）
	 */
	private int checkPwd(String value, String salt, String pwd){
		
		String encodePwdHash = EncodeUtils.SHA1Encode(salt+pwd);
		String encodeValue = DESUtils.getEncryptString(value);
		
		String sql = "SELECT c1.customer_id FROM Customer c1 WHERE c1.nickname = ?  AND c1.pwd = ?"
				   + "UNION ALL "
				   + "SELECT c2.customer_id FROM Customer c2 WHERE c2.email = ?  AND c2.pwd = ?"
				   + "UNION ALL "
				   + "SELECT c3.customer_id FROM Customer c3 WHERE c3.phone = ?  AND c3.pwd = ?";
		Query query = getSession().createSQLQuery(sql);
		List<Integer> result = query.setString(0,encodeValue).setString(1,encodePwdHash)
									.setString(2,encodeValue).setString(3,encodePwdHash)
									.setString(4,encodeValue).setString(5,encodePwdHash)
									.list();
		
		int customer_id = 0;
		if(result.size() > 0){
			customer_id = result.get(0);
		}
		return customer_id;
	}
	
	public boolean addCompanyMember(CompanyMember companyMember){
		int result = (int) getSession().save(companyMember);
		if(result > 0 ){
			return true;
		}
		else{
			return false;
		}
	}
	
	public CompanyMember getCompanyMemberByCID(int customerID){
		String hql = "FROM CompanyMember WHERE customerID = ?";   
		Query query = getSession().createQuery(hql);
		List<CompanyMember> result = query.setInteger(0,customerID).list();
		CompanyMember cm = null;
		if(result.size() > 0 ){
			cm = result.get(0);
		}
		return cm;
	}
	
 

	
}
