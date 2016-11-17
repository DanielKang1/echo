package com.echo.dao.hotelstaffdao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.echo.domain.po.HotelStaff;
import com.echo.utils.DESUtils;
import com.echo.utils.EncodeUtils;

@Repository
public class HotelStaffDAOImpl implements HotelStaffDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public HotelStaff get(int staffID) {
		String hql = "FROM HotelStaff WHERE staffID = ?";   
		Query query = getSession().createQuery(hql);
		List<HotelStaff> result = query.setInteger(0,staffID).list();
		HotelStaff staff = null;
		if(result.size() > 0 ){
			staff = result.get(0);
		}
		return staff;
	}

	@Override
	public HotelStaff get(String fieldValue, String pwd) {
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
		String sql = "SELECT h1.staff_id FROM HotelStaff h1 WHERE h1.staff_name = ?  AND h1.pwd = ?"
				   + "UNION ALL "
				   + "SELECT h2.staff_id FROM HotelStaff h2 WHERE h2.phone= ?  AND h2.pwd = ?";
		Query query = getSession().createSQLQuery(sql);
		List<Integer> result = query.setString(0,encodeValue).setString(1,encodePwdHash)
									.setString(2,encodeValue).setString(3,encodePwdHash)
									.list();
		int staff_id = 0;
		if(result.size() > 0){
			staff_id = result.get(0);
		}
		return staff_id;
	}
	
	
	//获取酒店管理人员的pwdsalt
	public String getPwdsalt(String value) {
		String encodeValue = DESUtils.getEncryptString(value);
		String sql = "SELECT h1.pwdsalt FROM HotelStaff h1 WHERE h1.staff_name = ?  "
				   + "UNION ALL "
				   + "SELECT h2.pwdsalt FROM HotelStaff h2 WHERE h2.phone = ?  ";
		Query query = getSession().createSQLQuery(sql);
		List<String> result = query.setString(0,encodeValue).setString(1,encodeValue).list();
		String pwdsalt = null;
		if(result.size() > 0){
			 pwdsalt = result.get(0);
		}
		return pwdsalt;
	}

	@Override
	public boolean update(HotelStaff hotelStaff) {
		try {
			getSession().update(hotelStaff);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean add(HotelStaff hotelStaff) {
		int result = (int) getSession().save(hotelStaff);
		if(result > 0 ){
			return true;
		}
		else{
			return false;
		}
	}

}
