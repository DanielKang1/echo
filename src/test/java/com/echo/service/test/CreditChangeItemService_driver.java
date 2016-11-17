package com.echo.service.test;

import com.echo.domain.po.CreditChangeItem;
import com.echo.service.creditservice.CreditService;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class CreditChangeItemService_driver {
    public void drive(CreditService creditChangeItemService){
        creditChangeItemService.generateItem(new CreditChangeItem());
        creditChangeItemService.getCustomerItem(0);
        creditChangeItemService.getOrderItem(0);
    }

    public static void main(String[] args) {
        (new CreditChangeItemService_driver()).drive(new CreditChangeItemService_stub());
    }
}
