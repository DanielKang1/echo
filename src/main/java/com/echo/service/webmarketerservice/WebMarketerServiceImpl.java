package com.echo.service.webmarketerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.echo.dao.webmarketerdao.WebMarketerDAOImpl;
import com.echo.domain.po.WebMarketer;
import com.echo.utils.DESUtils;
import com.echo.utils.EncodeUtils;

@Service
public class WebMarketerServiceImpl implements WebMarketerService {

	@Autowired
	private WebMarketerDAOImpl webMarketerDAOImpl;
	
	@Override
	public WebMarketer login(String fieldValue, String pwd) {
		return webMarketerDAOImpl.get(fieldValue, pwd);
	}

	@Override
	public boolean addMarketer(WebMarketer marketer) {
		marketer.setMarketerName(DESUtils.getEncryptString(marketer.getMarketerName()));
		String pwdsalt = EncodeUtils.getSalt(16);
		marketer.setPwdsalt(pwdsalt);
		String target = pwdsalt+marketer.getPwd();
		marketer.setPwd(EncodeUtils.SHA1Encode(target));
		return webMarketerDAOImpl.save(marketer);
	}

	@Override
	public WebMarketer getBasicInfo(int marketerID) {
		return webMarketerDAOImpl.get(marketerID);
	}
	
	@Override
	public WebMarketer getBasicInfo(String name) {
		return webMarketerDAOImpl.get(name);
	}

	@Override
	public boolean updateMarketer(WebMarketer marketer) {
		return webMarketerDAOImpl.update(marketer);
	}
	
	public WebMarketer decodeWebMarketer(WebMarketer marketer){
		marketer.setMarketerName(DESUtils.getDecryptString(marketer.getMarketerName()));
		return marketer;
	}



}
