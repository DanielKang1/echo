package com.echo.dao.test;

import java.util.List;

import com.echo.dao.creditdao.CreditDAO;
import com.echo.domain.po.CreditChangeItem;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class CreditDAO_stub implements CreditDAO{

    public boolean save(CreditChangeItem creditChangeItem) {
        System.out.println("save successfully");
        return false;
    }

    public List<CreditChangeItem> getByCID(int customerID) {
        System.out.println("show creditchangeitem");
        return null;
    }

    public List<CreditChangeItem> getByOID(int orderID) {
        System.out.println("show creditchangeitem");
        return null;
    }

	@Override
	public boolean add(CreditChangeItem creditChangeItem) {
		 System.out.println("add creditchangeitem");
		return false;
	}
}
