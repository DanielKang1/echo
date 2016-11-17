package com.echo.service.creditservice;

import java.util.List;

import com.echo.domain.po.CreditChangeItem;

public interface CreditService {

	/**
	 * 生成信用记录
	 * @param creditChangeItem
	 * @return
	 */
	public boolean generateItem(CreditChangeItem creditChangeItem);
	
	/**
	 * 获得用户的信用变化记录汇总
	 * @param customerID
	 * @return
	 */
	public List<CreditChangeItem> getCustomerItem(int customerID);
	
	/**
	 * 获得订单的信用变化记录
	 * @param orderID
	 * @return
	 */
	public List<CreditChangeItem> getOrderItem(int orderID);

}
