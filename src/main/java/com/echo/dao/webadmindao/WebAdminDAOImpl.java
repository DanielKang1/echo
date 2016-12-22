package com.echo.dao.webadmindao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.echo.domain.po.CusEnterItem;
import com.echo.domain.po.WebAdmin;
import com.echo.utils.DESUtils;
import com.echo.utils.EncodeUtils;

@Repository
public class WebAdminDAOImpl implements WebAdminDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
//	public boolean save(WebAdmin webMarketer) {
//		int result = (int) getSession().save(webMarketer);
//		if(result > 0 ){
//			return true;
//		}
//		else{
//			return false;
//		}
//	}
	
	@Override
	public WebAdmin get(int adminID) {
		return (WebAdmin)getSession().get(WebAdmin.class, adminID);
	}

	@Override
	public WebAdmin get(String name,String pwd) {
		String pwdsalt = getPwdsalt(name);
		if(pwdsalt != null){
			int result = checkPwd(name, pwdsalt, pwd);
				if(result != 0){
					return get(result);
				}
		}
		return null;
	}
	
	public String getPwdsalt(String value) {
		String encodeValue = DESUtils.getEncryptString(value);
		String sql = "SELECT w1.pwdsalt FROM webadmin w1 WHERE w1.admin_name = ? ";
		Query query = getSession().createSQLQuery(sql);
		List<String> result = query.setString(0,encodeValue).list();
		System.out.println(" "+result);
		String pwdsalt = null;
		if(result.size() > 0){
			 pwdsalt = result.get(0);
		}
		return pwdsalt;
	}
	
	public int checkPwd(String value, String salt, String pwd){
		String encodePwdHash = EncodeUtils.SHA1Encode(salt+pwd);
		String encodeValue = DESUtils.getEncryptString(value);
		String sql = "SELECT w1.admin_id FROM webadmin w1 WHERE w1.admin_name = ?  AND w1.pwd = ?";
		Query query = getSession().createSQLQuery(sql);
		List<Integer> result = query.setString(0,encodeValue).setString(1,encodePwdHash).list();
		int admin_id = 0;
		if(result.size() > 0){
			admin_id = result.get(0);
		}
		return admin_id;
	}

	@Override
	public boolean delete(int enterpriseID) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean addRelation(CusEnterItem item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CusEnterItem> getRelation(int enterpriseID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteRelation(int itemID) {
		// TODO Auto-generated method stub
		return false;
	}


}
