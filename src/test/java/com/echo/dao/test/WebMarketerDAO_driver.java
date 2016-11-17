package com.echo.dao.test;

import com.echo.dao.webmarketerdao.WebMarketerDAO;
import com.echo.domain.po.WebMarketer;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class WebMarketerDAO_driver {
    public void drive(WebMarketerDAO webMarketerDAO){
        webMarketerDAO.get(0);
        webMarketerDAO.get("a","123");
        webMarketerDAO.save(new WebMarketer());
        webMarketerDAO.update(new WebMarketer());
    }
    public void main(String[] args){
        (new WebMarketerDAO_driver()).drive(new WebMarketerDAO_stub()) ;
    }
}
