package com.echo.service.webadminservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.echo.dao.webadmindao.WebAdminDAOImpl;
import com.echo.domain.po.CusEnterItem;
import com.echo.domain.po.WebAdmin;
import com.echo.utils.DESUtils;
 

@Service
public class WebAdminServiceImpl implements WebAdminService{
	
	@Autowired
	private WebAdminDAOImpl webAdminDAOImpl;

	@Override
	public WebAdmin login(String name, String pwd) {
		return webAdminDAOImpl.get(name, pwd);
	}
	
	public WebAdmin decodeWebAdmin(WebAdmin admin){
		admin.setAdminName(DESUtils.getDecryptString(admin.getAdminName()));
		return admin;
	}
	
//	public boolean addMarketer(WebAdmin marketer) {
//		marketer.setAdminName(DESUtils.getEncryptString(marketer.getAdminName()));
//		String pwdsalt = EncodeUtils.getSalt(16);
//		marketer.setPwdsalt(pwdsalt);
//		String target = pwdsalt+marketer.getPwd();
//		marketer.setPwd(EncodeUtils.SHA1Encode(target));
//		return webAdminDAOImpl.save(marketer);
//	}
	

	@Override
	public boolean deleteEnterprise(int enterpriseID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEnterpriseName(String newName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addCusEnterItem(CusEnterItem item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCusEnterItem(int itemID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CusEnterItem> getCusEnterItems(int enterpriseID) {
		// TODO Auto-generated method stub
		return null;
	}

}
