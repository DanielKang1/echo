package com.echo.dao.webadmindao;

import java.util.List;
import com.echo.domain.po.CusEnterItem;
import com.echo.domain.po.WebAdmin;

public interface WebAdminDAO {
	
	public WebAdmin get(int adminID);
	
	/**
	 * 按照name和密码获取该WebAdmin对象
	 * @param webAdminID
	 * @param pwd
	 * @return
	 */
	public WebAdmin get(String name,String pwd);
	
	/**
	 * 删除合作企业
	 * @param enterpriseID
	 * @return
	 */
	public boolean delete(int enterpriseID);
	
	
	/**
	 * 添加企业与企业中的普通用户的关联关系
	 * @param enterpriseID
	 * @param customerID
	 * @return
	 */
	public boolean addRelation(CusEnterItem item);
	
	/**
	 * 获得合作的所有Customer
	 * @param enterpriseID
	 * @return
	 */
	public List<CusEnterItem> getRelation(int enterpriseID);

	/**
	 * 删除Enterprise和Customer的关联关系
	 * @param itemID
	 * @return
	 */
	public boolean deleteRelation(int itemID);

}
