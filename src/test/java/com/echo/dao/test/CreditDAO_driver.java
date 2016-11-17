package com.echo.dao.test;

import com.echo.dao.creditdao.CreditDAO;
import com.echo.domain.po.CreditChangeItem;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class CreditDAO_driver {
    public void drive(CreditDAO creditChangeItemDAO) {
        creditChangeItemDAO.add(new CreditChangeItem());
        creditChangeItemDAO.getByCID(0);
        creditChangeItemDAO.getByOID(0);
        creditChangeItemDAO.add(new CreditChangeItem());
    }

    public void main(String[] args){
        (new CreditDAO_driver()).drive(new CreditDAO_stub());
    }
}
