package com.echo.dao.webpromotiondao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.echo.domain.po.DistrictDiscount;
import com.echo.domain.po.MemberDiscount;
import com.echo.domain.po.PromotionDate;

@Repository
public class WebPromotionDAOImpl implements WebPromotionDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean update(MemberDiscount memberDiscount) {
		try {
			getSession().update(memberDiscount);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean add(MemberDiscount memberDiscount) {
		int result = (int) getSession().save(memberDiscount);
		if(result > 0 ){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean deleteMemberDiscount(int levelID) {
		String hql = "DELETE FROM MemberDiscount m WHERE m.levelID = :levelID";
		Query query = getSession().createQuery(hql).setInteger("levelID", levelID);
		try {
			query.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public MemberDiscount getMemberDiscount(int levelID) { 
		String hql = "FROM MemberDiscount WHERE levelID = ?";    //levelID就是等级
		Query query = getSession().createQuery(hql);
		List<MemberDiscount> result = query.setInteger(0,levelID).list();
		MemberDiscount memberDiscount = null;
		if(result.size() > 0 ){
			memberDiscount = result.get(0);
		}
		return memberDiscount;
	}
	
	public List<MemberDiscount> getAllMemberDiscount(){
		String hql = "FROM MemberDiscount";    
		Query query = getSession().createQuery(hql);
		List<MemberDiscount> result = query.list();
		return result;
	}

	@Override
	public MemberDiscount getMemberDiscountByCredit(double credit){
		String hql = "FROM MemberDiscount WHERE creditLimit <= ? AND levelID <= 5 ORDER BY creditLimit DESC";   
		Query query = getSession().createQuery(hql);
		List<MemberDiscount> result = query.setDouble(0,credit).list();
		MemberDiscount memberDiscount = null;
		if(result.size() > 0 ){
			memberDiscount = result.get(0);
		}
		return memberDiscount;
	}

	@Override
	public boolean add(DistrictDiscount districtDiscount) {
		int result = (int) getSession().save(districtDiscount);
		if(result > 0 ){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean update(DistrictDiscount districtDiscount) {
		try {
			getSession().update(districtDiscount);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteDistrictDiscount(int districtDiscountID) {
		String hql = "DELETE FROM DistrictDiscount m WHERE m.id = :id";
		Query query = getSession().createQuery(hql).setInteger("id", districtDiscountID);
		try {
			query.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public DistrictDiscount getDistrictDiscount(int districtDiscountID) {
		String hql = "FROM DistrictDiscount WHERE id = ?";   
		Query query = getSession().createQuery(hql);
		List<DistrictDiscount> result = query.setInteger(0,districtDiscountID).list();
		DistrictDiscount districtDiscount = null;
		if(result.size() > 0 ){
			districtDiscount = result.get(0);
		}
		return districtDiscount;
	}
	
	@Override
	public DistrictDiscount getDistrictDiscount(String districtName) {
		String hql = "FROM DistrictDiscount WHERE districtName = ?";   
		Query query = getSession().createQuery(hql);
		List<DistrictDiscount> result = query.setString(0,districtName).list();
		DistrictDiscount districtDiscount = null;
		if(result.size() > 0 ){
			districtDiscount = result.get(0);
		}
		return districtDiscount;
	}

	@Override
	public List<DistrictDiscount> getAll() {
		String hql = "FROM DistrictDiscount";   
		Query query = getSession().createQuery(hql);
		List<DistrictDiscount> result = query.list();
		return result;
	}

	@Override
	public boolean addPromotionDates(PromotionDate promotionDate) {
		int result = (int) getSession().save(promotionDate);
		if(result > 0 ){
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public List<PromotionDate> getPromotionDateList() {
		String hql = "FROM PromotionDate WHERE hotelID = 0";   
		Query query = getSession().createQuery(hql);
		List<PromotionDate> result = query.list();
		return result;
	}

	@Override
	public boolean updateDatesPromotion(PromotionDate promotionDate) {
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

	
	

	 

}
