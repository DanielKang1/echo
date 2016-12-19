package com.echo.dao.webmarketerdao;

import com.echo.domain.po.WebMarketer;

public interface WebMarketerDAO {
	
	/**
	 * 按照ID获取网站营销人员
	 * @param marketerID
	 * @return
	 */
	public WebMarketer get(int marketerID);
	
	public WebMarketer get(String name);
	
	/**
	 * 用于网站营销人员的登录
	 * @param fieldValue Name/ID中的一种值
	 * @param pwd 输入的密码
	 * @return
	 */
	public WebMarketer get(String fieldValue,String pwd);
	
	/**
	 * 更新营销人员的信息
	 * @param webMarketer
	 * @return
	 */
	public boolean update(WebMarketer webMarketer);
	
	/**
	 * 添加网站营销人员 
	 * @param webMarketer
	 * @return
	 */
	public boolean save(WebMarketer webMarketer);

}
