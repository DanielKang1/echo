package com.echo.service.creditservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.echo.dao.creditdao.CreditDAOImpl;
import com.echo.domain.po.CreditChangeItem;

@Service
public class CreditServiceImpl implements CreditService {
	
	@Autowired
	public CreditDAOImpl creditDAOImpl;

	@Override
	public boolean generateItem(CreditChangeItem creditChangeItem) {
		return creditDAOImpl.add(creditChangeItem);
	}

	@Override
	public List<CreditChangeItem> getCustomerItem(int customerID) {
		return creditDAOImpl.getByCID(customerID);
	}

	@Override
	public List<CreditChangeItem> getOrderItem(int orderID) {
		return creditDAOImpl.getByOID(orderID);
	}

}
