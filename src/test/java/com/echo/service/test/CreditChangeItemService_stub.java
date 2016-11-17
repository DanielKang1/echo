package com.echo.service.test;

import java.util.List;

import com.echo.domain.po.CreditChangeItem;
import com.echo.service.creditservice.CreditService;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class CreditChangeItemService_stub implements CreditService {
    public boolean generateItem(CreditChangeItem creditChangeItem) {
        System.out.println("generateitem successfully");
        return false;
    }

    public List<CreditChangeItem> getCustomerItem(int customerID) {
        System.out.println("show CreditChangeItem");
        return null;
    }

    public List<CreditChangeItem> getOrderItem(int orderID) {
        System.out.println("show CreditChangeItem");
        return null;
    }
}
