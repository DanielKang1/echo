package com.echo.dao.webadmindao;

import java.util.List;

import com.echo.domain.po.CusEnterItem;
import com.echo.domain.po.Enterprise;
import com.echo.domain.po.WebAdmin;

public class WebAdminDAOImpl implements WebAdminDAO{

	@Override
	public WebAdmin get(int webAdminID, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Enterprise enterprise) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int enterpriseID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Enterprise enterprise) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addRelation(CusEnterItem item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CusEnterItem> getRelation(int enterpriseID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteRelation(int itemID) {
		// TODO Auto-generated method stub
		return false;
	}

}
