package com.echo.dao.creditdao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.echo.dao.basedao.BaseDaoImpl;
import com.echo.domain.po.CreditChangeItem;

@Repository
public class CreditDAOImpl implements CreditDAO {

	@Autowired
	public SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean add(CreditChangeItem creditChangeItem) {
		int result = (int) getSession().save(creditChangeItem);
		if(result > 0 ){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public List<CreditChangeItem> getByCID(int customerID) {
		String hql = "FROM CreditChangeItem WHERE customerID = ?";
		Query query = getSession().createQuery(hql);
		List<CreditChangeItem> result = query.setInteger(0, customerID).list();
		return result;
	}

	@Override
	public List<CreditChangeItem> getByOID(int orderID) {
		String hql = "FROM CreditChangeItem WHERE orderID = ?";
		Query query = getSession().createQuery(hql);
		List<CreditChangeItem> result = query.setInteger(0, orderID).list();
		return result;
	}

}
