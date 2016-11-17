package com.echo.service.webadminservice;

import java.util.List;

import com.echo.domain.po.CusEnterItem;
import com.echo.domain.po.Enterprise;

public interface WebAdminService {
	
	/**
	 * 网站管理员登录
	 * @param webAdminID
	 * @param pwd
	 */
	public void login(int webAdminID, String pwd);
	
	/**
	 * 添加合作企业
	 * @param enterprise
	 * @return
	 */
	public boolean addEnterprise(Enterprise enterprise);
	
	/**
	 * 删除合作企业
	 * @param enterpriseID
	 * @return
	 */
	public boolean deleteEnterprise(int enterpriseID);
	
	/**
	 * 更新合作企业名称
	 * @param newName
	 * @return
	 */
	public boolean updateEnterpriseName(String newName);
	
	/**
	 * 添加Customer和Enterprise的关联关系
	 * @param item
	 * @return
	 */
	public boolean addCusEnterItem(CusEnterItem item);
	
	/**
	 * 删除Customer和Enterprise的关联关系
	 * @param itemID
	 * @return
	 */
	public boolean deleteCusEnterItem(int itemID);
	
	/**
	 * 获取Enterprise的关联关系列表
	 * @param enterpriseID
	 * @return
	 */
	public List<CusEnterItem> getCusEnterItems(int enterpriseID);

}
