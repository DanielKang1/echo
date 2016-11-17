package com.echo.service.test;

import com.echo.domain.po.WebMarketer;
import com.echo.service.webmarketerservice.WebMarketerService;

public class WebMarketerService_stub implements WebMarketerService {
    public boolean login(String fieldValue, String pwd) {
        System.out.println("login successfully");
        return false;
    }

	@Override
	public boolean addMarketer(WebMarketer marketer) {
		System.out.println("addMarketer successfully");
		return false;
	}

	@Override
	public WebMarketer getBasicInfo(int marketerID) {
		System.out.println("getBasicInfo successfully");
		return null;
	}

	@Override
	public boolean updateMarketer(WebMarketer marketer) {
		System.out.println("updateMarketer successfully");
		return false;
	}
 

 
}
