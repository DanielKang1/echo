package com.echo.service.webmarketerservice;

import com.echo.domain.po.WebMarketer;

public interface WebMarketerService {
	
	/**
	 * 网站营销人员的登录
	 * @param fieldValue Name/ID中的一种
	 * @param pwd 输入的密码
	 * @return
	 */
	public WebMarketer login(String fieldValue,String pwd);
	
	/**
	 * 添加营销人员
	 * @param marketer
	 * @return
	 */
	public boolean addMarketer(WebMarketer marketer);
	
	/**
	 * 获得网站营销人员的基础信息
	 * @param marketerID
	 * @return
	 */
	public WebMarketer getBasicInfo(int marketerID);
	
	public WebMarketer getBasicInfo(String name);
	
	/**
	 * 更新营销人员信息
	 * @param marketer
	 * @return
	 */
	public boolean updateMarketer(WebMarketer marketer);

}
