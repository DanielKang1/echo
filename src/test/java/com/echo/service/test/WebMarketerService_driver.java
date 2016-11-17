package com.echo.service.test;

import com.echo.domain.po.WebMarketer;
import com.echo.service.webmarketerservice.WebMarketerService;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class WebMarketerService_driver {
    public void drive(WebMarketerService webMarketerService){
        webMarketerService.addMarketer(new WebMarketer());
        webMarketerService.getBasicInfo(0);
        webMarketerService.login("abc","123");
        webMarketerService.updateMarketer(new WebMarketer());
    }

    public static void main(String[] args) {
        (new WebMarketerService_driver()).drive(new WebMarketerService_stub());
    }
}
