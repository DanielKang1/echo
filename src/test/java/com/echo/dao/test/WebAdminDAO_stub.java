package com.echo.dao.test;

import java.util.List;

import com.echo.dao.webadmindao.WebAdminDAO;
import com.echo.domain.po.CusEnterItem;
import com.echo.domain.po.Enterprise;
import com.echo.domain.po.WebAdmin;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class WebAdminDAO_stub implements WebAdminDAO {
    public WebAdmin get(int webAdminID, String pwd) {
        System.out.println("show webadmin");
        return null;
    }

    public boolean add(Enterprise enterprise) {
        System.out.println("add successfully");
        return false;
    }

    public boolean delete(int enterpriseID) {
        System.out.println("delete successfully");
        return false;
    }

    public boolean update(Enterprise enterprise) {
        System.out.println("update successfully");
        return false;
    }

    public boolean addRelation(CusEnterItem item) {
        System.out.println("addrelation successfully");
        return false;
    }

    public List<CusEnterItem> getRelation(int enterpriseID) {
        System.out.println("show cusenteritem");
        return null;
    }

    public boolean deleteRelation(int itemID) {
        System.out.println("deleterelation successfully");
        return false;
    }
}
