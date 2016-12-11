package com.echo.dao.evaluationdao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.echo.domain.po.Evaluation;
import com.echo.domain.po.Hotel;

@Repository
public class EvaluationDAOImpl implements EvaluationDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean add(Evaluation evaluation) {
		int result = (int) getSession().save(evaluation);
		if(result > 0 ){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean delete(int evaluationID) {
		String hql = "DELETE FROM Evaluation e WHERE e.evaluationID = :evaluationID";
		Query query = getSession().createQuery(hql).setInteger("evaluationID", evaluationID);
		try {
			query.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public List<Evaluation> getByHotelID(int hotelID) {
		String hql = "FROM Evaluation WHERE hotelID = ?";   
		Query query = getSession().createQuery(hql);
		List<Evaluation> result = query.setInteger(0,hotelID).list();
		return result;
	}

	@Override
	public List<Evaluation> getByCusID(int customerID) {
		String hql = "FROM Evaluation WHERE customerID = ?";   
		Query query = getSession().createQuery(hql);
		List<Evaluation> result = query.setInteger(0,customerID).list();
		return result;
	}

}
