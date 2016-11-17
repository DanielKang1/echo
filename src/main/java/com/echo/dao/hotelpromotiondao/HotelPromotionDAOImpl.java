package com.echo.dao.hotelpromotiondao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.echo.domain.po.Hotel;
import com.echo.domain.po.HotelPromotionItem;
import com.echo.domain.po.PromotionDate;

@Repository
public class HotelPromotionDAOImpl implements HotelPromotionDAO {
	

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean add(PromotionDate promotionDate) {
		int result = (int) getSession().save(promotionDate);
		if(result > 0 ){
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public List<PromotionDate> getPromotionDateList(int hotelID) {
		String hql = "FROM PromotionDate WHERE hotelID = ?";   
		Query query = getSession().createQuery(hql).setInteger(0,hotelID);
		List<PromotionDate> result = query.list();
		return result;
	}

	@Override
	public boolean update(PromotionDate promotionDate) {
		try {
			getSession().update(promotionDate);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteDatesPromotion(int id) {
		String hql = "DELETE FROM PromotionDate p WHERE p.id = :id";
		Query query = getSession().createQuery(hql).setInteger("id", id);
		try {
			query.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public HotelPromotionItem getHotelPromotionItem(int hotelID) {
		String hql = "FROM HotelPromotionItem WHERE hotelID = ?";   
		Query query = getSession().createQuery(hql);
		List<HotelPromotionItem> result = query.setInteger(0,hotelID).list();
		HotelPromotionItem hotelPromotionItem = null;
		if(result.size() > 0 ){
			hotelPromotionItem = result.get(0);
		}
		return hotelPromotionItem;
	}

	@Override
	public boolean add(HotelPromotionItem hotelPromotionItem) {
		int result = (int) getSession().save(hotelPromotionItem);
		if(result > 0 ){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean update(HotelPromotionItem hotelPromotionItem) {
		try {
			getSession().update(hotelPromotionItem);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}
