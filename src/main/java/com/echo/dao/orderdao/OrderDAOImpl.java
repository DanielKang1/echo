package com.echo.dao.orderdao;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.echo.domain.po.Order;
import com.echo.domain.type.OrderStatusType;

@Repository
public class OrderDAOImpl implements OrderDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean add(Order order) {
		int result = (int) getSession().save(order);
		if(result > 0 ){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean update(Order order) {
		try {
			getSession().update(order);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Order get(int orderID) {
		return (Order)getSession().get(Order.class,orderID);
	}
	
	public List<Order> getUnexecutedOrders() {
		String hql = "FROM Order WHERE orderType = 0";   
		Query query = getSession().createQuery(hql);
		List<Order> result = query.list();
		return result;
	}

	@Override
	public List<Order> getOrdersByCID(int customerID) {
		String hql = "FROM Order WHERE customerID = ?";   
		Query query = getSession().createQuery(hql);
		List<Order> result = query.setInteger(0,customerID).list();
		return result;
	}

	@Override
	public List<Order> getOrdersByCIDAndHID(int customerID, int hotelID) {
		String hql = "FROM Order WHERE customerID = ? AND hotel.hotelID = ?";   
		Query query = getSession().createQuery(hql);
		List<Order> result = query.setInteger(0,customerID).setInteger(1,hotelID).list();
		return result;
	}

	@Override
	public List<Order> getOrdersByCIDAndOrderType(int customerID, byte orderTypeID) {
		String hql = "FROM Order WHERE customerID = ? AND orderType = ?";   
		Query query = getSession().createQuery(hql);
		List<Order> result = query.setInteger(0,customerID).setByte(1,orderTypeID).list();
		return result;
	}

	@Override
	public List<Order> getOrdersByHID(int hotelID) {
		String hql = "FROM Order WHERE hotel.hotelID = ? ";   
		Query query = getSession().createQuery(hql);
		List<Order> result = query.setInteger(0,hotelID).list();
		return result;
	}

	@Override
	public List<Order> getOrdersByHIDAndOrderType(int hotelID, byte orderTypeID) {
		String hql = "FROM Order WHERE hotel.hotelID = ? AND orderType = ?";   
		Query query = getSession().createQuery(hql);
		List<Order> result = query.setInteger(0,hotelID).setByte(1,orderTypeID).list();
		return result;
	}

	@Override
	public List<Order> getOrdersByHIDAndCPhone(int hotelID, String phone) {
		String hql = "FROM Order WHERE hotel.hotelID = ? AND reservedPhone = ? AND orderType = ?";   
		Query query = getSession().createQuery(hql);
		List<Order> result = query.setInteger(0,hotelID).setString(1,phone).setByte(2, OrderStatusType.UNEXECUTED).list();
		List<Order> result2 = query.setInteger(0,hotelID).setString(1,phone).setByte(2, OrderStatusType.ABNORMAL).list();
		result.addAll(result2);
		return result;
	}

	@Override
	public List<Order> getOrdersByCPhone(String phone, byte orderType) {
		String hql = "FROM Order WHERE reservedPhone = ? AND orderType = ?";   
		Query query = getSession().createQuery(hql);
		List<Order> result = query.setString(0,phone).setByte(1, orderType).list();
		return result;
	}

	@Override
	public List<Order> getOrdersByTypeToday(byte orderTypeID) {
		String hql = "FROM Order WHERE TO_DAYS(NOW( )) - TO_DAYS(CREATION_DATE) <= 1  ";   
		Query query = getSession().createQuery(hql);
		List<Order> result = query.list();
		return result;
	}

	@Override
	public int getOrdersSizeByType(int hotelID, byte orderType) {
		String sql = "SELECT count(1) FROM c_order WHERE hotel_id = ? AND order_type = ?";
		Query query = getSession().createSQLQuery(sql);
		Object count = query.setInteger(0,hotelID).setByte(1,orderType).uniqueResult();  //BigInteger
		return  ((BigInteger)count).intValue();
	}
	
	public List<Order> getAbnormalOrdersToday(){
		String hql = "FROM Order o WHERE orderType = "+OrderStatusType.ABNORMAL+" AND Date(o.latestDate) = :date";
		Query query = getSession().createQuery(hql);
		query.setDate("date", Calendar.getInstance().getTime());
		List<Order> result = query.list();
		return result;
	}

}
