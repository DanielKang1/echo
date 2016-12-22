package com.echo.dao.webmarketerdao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.echo.domain.po.WebAdmin;
import com.echo.domain.po.WebMarketer;
import com.echo.utils.DESUtils;
import com.echo.utils.EncodeUtils;

@Repository
public class WebMarketerDAOImpl implements WebMarketerDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public WebMarketer get(int marketerID) {
		return (WebMarketer)getSession().get(WebMarketer.class, marketerID);
	}
	
	@Override
	public WebMarketer get(String name) {
		String hql = "FROM WebMarketer WHERE marketerName = ?";   
		Query query = getSession().createQuery(hql);
		List<WebMarketer> result = query.setString(0,DESUtils.getEncryptString(name)).list();
		WebMarketer marketer = null;
		if(result.size() > 0 ){
			marketer = result.get(0);
		}
		return marketer;
	}

	@Override
	public WebMarketer get(String fieldValue, String pwd) {
		String pwdsalt = getPwdsalt(fieldValue);
		if(pwdsalt != null){
			int result = checkPwd(fieldValue, pwdsalt, pwd);
				if(result != 0){
					return get(result);
				}
		}
		return null;
	}
	
	public int checkPwd(String value, String salt, String pwd){
		String encodePwdHash = EncodeUtils.SHA1Encode(salt+pwd);
		String encodeValue = DESUtils.getEncryptString(value);
		String sql = "SELECT w1.marketer_ID FROM webmarketer w1 WHERE w1.marketer_Name = ?  AND w1.pwd = ?";
		Query query = getSession().createSQLQuery(sql);
		List<Integer> result = query.setString(0,encodeValue).setString(1,encodePwdHash).list();
		int marketer_id = 0;
		if(result.size() > 0){
			marketer_id = result.get(0);
		}
		return marketer_id;
	}
	
	
	public String getPwdsalt(String value) {
		String encodeValue = DESUtils.getEncryptString(value);
		String sql = "SELECT w2.pwdsalt FROM webmarketer w2 WHERE w2.marketer_Name = ? ";
		Query query = getSession().createSQLQuery(sql);
		List<String> result = query.setString(0,encodeValue).list();
		String pwdsalt = null;
		if(result.size() > 0){
			 pwdsalt = result.get(0);
		}
		return pwdsalt;
	}

	@Override
	public boolean update(WebMarketer webMarketer) {
		try {
			getSession().update(webMarketer);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean save(WebMarketer webMarketer) {
		int result = (int) getSession().save(webMarketer);
		if(result > 0 ){
			return true;
		}
		else{
			return false;
		}
	}


}
