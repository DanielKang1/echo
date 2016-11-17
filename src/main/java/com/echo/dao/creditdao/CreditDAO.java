package com.echo.dao.creditdao;

import java.util.List;

import com.echo.domain.po.CreditChangeItem;

public interface CreditDAO {
	
	/**
	 * 保存信用变化记录
	 * @param creditChangeItem
	 * @return
	 */
	public boolean add(CreditChangeItem creditChangeItem);
	
	/**
	 * 获得用户的所有变化记录
	 * @param customerID
	 * @return
	 */
	public List<CreditChangeItem> getByCID(int customerID);
	
	/**
	 * 获得某条订单的所有信用变化记录
	 * @param orderID
	 * @return
	 */
	public List<CreditChangeItem> getByOID(int orderID);
	

}
