package com.echo.service.test;

import com.echo.domain.po.CusEnterItem;
import com.echo.domain.po.Enterprise;
import com.echo.service.webadminservice.WebAdminService;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class WebAdminService_driver {
    public void drive(WebAdminService webAdminService){
        webAdminService.addCusEnterItem(new CusEnterItem());
        webAdminService.addEnterprise(new Enterprise());
        webAdminService.deleteCusEnterItem(0);
        webAdminService.deleteEnterprise(0);
        webAdminService.getCusEnterItems(0);
        webAdminService.login(0,"abc");
        webAdminService.updateEnterpriseName("abc");
    }

    public static void main(String[] args) {
        (new WebAdminService_driver()).drive(new WebAdminService_stub());
    }
}
