package com.echo.service.hotelstaffservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.echo.dao.hoteldao.HotelDAOImpl;
import com.echo.dao.hotelstaffdao.HotelStaffDAOImpl;
import com.echo.domain.po.HotelStaff;
import com.echo.utils.DESUtils;
import com.echo.utils.EncodeUtils;

@Service
public class HotelStaffServiceImpl implements HotelStaffService{
	
	@Autowired
	public HotelStaffDAOImpl hotelStaffDAOImpl;

	@Override
	public HotelStaff login(String fieldValue, String pwd) {
		return hotelStaffDAOImpl.get(fieldValue, pwd);
	}

	@Override
	public boolean addStaff(int hotelID,String phone,String staffName,String pwd) {
		HotelStaff staff = new HotelStaff();
		staff.setHotelID(hotelID);
		staff.setPhone(DESUtils.getEncryptString(phone));
		staff.setStaffName(DESUtils.getEncryptString(staffName));
		String pwdsalt = EncodeUtils.getSalt(16);
		staff.setPwdsalt(pwdsalt);
		String target = pwdsalt+pwd;
		staff.setPwd(EncodeUtils.SHA1Encode(target));
		return hotelStaffDAOImpl.add(staff);
	}

	@Override
	public HotelStaff getBasicInfo(int staffID) {
		return hotelStaffDAOImpl.get(staffID);
	}

	@Override
	public boolean updateStaff(HotelStaff staff) {
		return hotelStaffDAOImpl.update(staff);
	}
	
	/**
	 * 解密HotelStaff
	 */
	public HotelStaff decodeHotelStaff(HotelStaff staff){
		staff.setStaffName(DESUtils.getDecryptString(staff.getStaffName()));
		staff.setPhone(DESUtils.getDecryptString(staff.getPhone()));
		return staff;
	}

}
