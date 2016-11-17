package com.echo.service.test;

import java.util.List;

import com.echo.domain.po.CusEnterItem;
import com.echo.domain.po.Enterprise;
import com.echo.service.webadminservice.WebAdminService;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class WebAdminService_stub implements WebAdminService {
    public void login(int webAdminID, String pwd) {
        System.out.println("login successfully");
    }

    public boolean addEnterprise(Enterprise enterprise) {
        System.out.println("addEnterprise successfully");
        return false;
    }

    public boolean deleteEnterprise(int enterpriseID) {
        System.out.println("deleteEnterprise successfully");
        return false;
    }

    public boolean updateEnterpriseName(String newName) {
        System.out.println("updateEnterpriseName successfully");
        return false;
    }

    public boolean addCusEnterItem(CusEnterItem item) {
        System.out.println("addCusEnterItem successfully");
        return false;
    }

    public boolean deleteCusEnterItem(int itemID) {
        System.out.println("deleteCusEnterItem successfully");
        return false;
    }

    public List<CusEnterItem> getCusEnterItems(int enterpriseID) {
        System.out.println("show CusEnterItem");
        return null;
    }
}
