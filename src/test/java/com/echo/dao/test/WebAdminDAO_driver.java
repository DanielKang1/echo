package com.echo.dao.test;

import com.echo.dao.webadmindao.WebAdminDAO;
import com.echo.domain.po.CusEnterItem;
import com.echo.domain.po.Enterprise;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class WebAdminDAO_driver {
    public void drive(WebAdminDAO webAdminDAO) {
        webAdminDAO.delete(0);
        webAdminDAO.add(new Enterprise());
        webAdminDAO.addRelation(new CusEnterItem());
        webAdminDAO.deleteRelation(0);
        webAdminDAO.get(0,"abc");
        webAdminDAO.getRelation(0);
        webAdminDAO.update(new Enterprise());
    }

    public void main(String[] args){
        (new WebAdminDAO_driver()).drive(new WebAdminDAO_stub()) ;
    }
}
