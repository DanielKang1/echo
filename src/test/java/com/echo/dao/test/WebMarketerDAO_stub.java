package com.echo.dao.test;

import com.echo.dao.webmarketerdao.WebMarketerDAO;
import com.echo.domain.po.WebMarketer;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class WebMarketerDAO_stub implements WebMarketerDAO {
    public WebMarketer get(int marketerID) {
        System.out.println("show webmarketer");
        return null;
    }

    public WebMarketer get(String fieldValue, String pwd) {
        System.out.println("show webmarketer");
        return null;
    }

    public boolean update(WebMarketer webMarketer) {
        System.out.println("update successfully");
        return false;
    }

    public boolean save(WebMarketer webMarketer) {
        System.out.println("save successfully");
        return false;
    }
}
