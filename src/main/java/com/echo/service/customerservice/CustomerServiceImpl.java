package com.echo.service.customerservice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.echo.dao.customerdao.CustomerDAOimpl;
import com.echo.dao.webpromotiondao.WebPromotionDAOImpl;
import com.echo.domain.po.CompanyMember;
import com.echo.domain.po.Customer;
import com.echo.domain.po.MemberDiscount;
import com.echo.domain.type.CustomerAttributeType;
import com.echo.domain.type.MemberLevelType;
import com.echo.utils.DESUtils;
import com.echo.utils.EncodeUtils;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	public CustomerDAOimpl customerDAOimpl;
	
	@Autowired
	public WebPromotionDAOImpl webPromotionDAOImpl;
	
	
	@Override
	public boolean register(Customer customer) {
		return customerDAOimpl.add(customer);
	}

	@Override
	public Customer login(String value, String pwd) {
		return customerDAOimpl.get(value, pwd);
	}

	@Override
	public boolean modifyInfo(Customer customer) {
		//修改信息时，由于给用户显示的是从数据库取出来解密的原文，所以保存修改时，还是要将用户名、手机、邮箱加密
		customer.setNickname(DESUtils.getEncryptString(customer.getNickname()));
		customer.setEmail(DESUtils.getEncryptString(customer.getEmail()));
		customer.setPhone(DESUtils.getEncryptString(customer.getPhone()));
		boolean flag = customerDAOimpl.update(customer);
		return flag;
	}
	
	@Override
	public boolean modifyPwd(Customer customer,String newpwd) {
		customer.setNickname(DESUtils.getEncryptString(customer.getNickname()));
		customer.setEmail(DESUtils.getEncryptString(customer.getEmail()));
		customer.setPhone(DESUtils.getEncryptString(customer.getPhone()));
		customer.setPwd(EncodeUtils.SHA1Encode(customer.getPwdsalt()+newpwd));
		boolean flag = customerDAOimpl.update(customer);
		return flag;
	}

	@Override
	public Customer getBasicInfo(int custoermID) {
		return customerDAOimpl.get(custoermID);
	}
	
	@Override
	public Customer getBasicInfo(String name) {
		return customerDAOimpl.get(name);
	}


//	public boolean beMember(Customer customer) {
//		encodeCustomer2(customer);
//		MemberDiscount  md = webPromotionDAOImpl.getMemberDiscountByCredit(customer.getCredit());
//		customer.setGrade((byte)md.getLevelID());
//		return customerDAOimpl.update(customer);
//	}
//	
//	public boolean beVIPMember(Customer customer) {
//		encodeCustomer2(customer);
//		MemberDiscount  md = webPromotionDAOImpl.getMemberDiscountByCredit(customer.getCredit());
//		customer.setGrade((byte)(md.getLevelID()+5));
//		return customerDAOimpl.update(customer);
//	}
	
	public boolean updateMemberLevel(Customer customer){
		MemberDiscount  md = webPromotionDAOImpl.getMemberDiscountByCredit(customer.getCredit());
		if(md == null){
			md = webPromotionDAOImpl.getMemberDiscount(MemberLevelType.Level1); //当用户的信用值小于0时，getMemberDiscountByCredit是取不到值的，所以直接赋值为初始级别。
		}
		customer.setGrade((byte)md.getLevelID());
		return customerDAOimpl.update(customer);
	}
	
	

	@Override
	public boolean modifyCredit(Customer customer, double amount) {
		customer.setCredit(customer.getCredit() + amount);
		return updateMemberLevel(customer);
	}
	
	/**
	 * 对用户信息进行加密
	 */
	@Override
	public void encodeCustomer(Customer customer) {
		//随机生成密码所需的盐
		customer.setPwdsalt(EncodeUtils.getSalt(16));
		//用户填写的初试密码放到pwd处，得到的target为用户初试密码与盐相加后的值
		String target = customer.getPwdsalt()+customer.getPwd();
		//此时pwd为真正的密码散列值(SHA1)
		customer.setPwd(EncodeUtils.SHA1Encode(target));
		
		//对用户名，联系方式，邮箱进行加密（DES）
		encodeCustomer2(customer);
	}
	
	/**
	 * 对用户信息进行解密
	 */
	@Override
	public void decodeCustomer(Customer customer) {
		customer.setNickname(DESUtils.getDecryptString(customer.getNickname()));
		customer.setEmail(DESUtils.getDecryptString(customer.getEmail()));
		customer.setPhone(DESUtils.getDecryptString(customer.getPhone()));
	}
	
	public void encodeCustomer2(Customer customer) {
		customer.setNickname(DESUtils.getEncryptString(customer.getNickname()));
		customer.setEmail(DESUtils.getEncryptString(customer.getEmail()));
		customer.setPhone(DESUtils.getEncryptString(customer.getPhone()));
	}
	

	/**
	 * Hibernate Validator无法验证的 需要额外处理
	 */
	@Override
	public boolean validator(Customer customer, BindingResult result) {
		boolean pwdVal = pwdValidator(customer);
		boolean nameHasSameVal = nameValidator2(customer);
		boolean phoneHasSameVal = phoneValidator2(customer);
		boolean emailHasSameVal = emailValidator2(customer);
		if(!pwdVal)
			result.rejectValue("pwdhash", "PwdLengthError");
		if(!phoneHasSameVal)
			result.rejectValue("phone", "HasSamePhoneError");
		if(!nameHasSameVal)
			result.rejectValue("nickname", "HasSameNameError");
		if(!emailHasSameVal)
			result.rejectValue("email", "HasSameEmailError");
		return  pwdVal && nameHasSameVal && phoneHasSameVal && emailHasSameVal &&validator_(customer,result);
	}
	
	//因为修改信息时还要再一次验证，所以只能把手机、用户名、邮箱的格式验证单独抽取出来整合在一起
	public boolean validator_(Customer customer, BindingResult result){
		boolean phoneVal = phoneValidator(customer);
		boolean nameLenVal = nameValidator1(customer);
		boolean emailVal = emailValidator(customer);
		
		if(!phoneVal)
			result.rejectValue("phone", "PhoneTypeError");
		
		if(!nameLenVal)
			result.rejectValue("nickname", "NameLengthError");
		
		if(!emailVal)
			result.rejectValue("email", "EmailError");
		
		return phoneVal && nameLenVal && emailVal;
	}
	
	public boolean validator_modify(Customer customer, BindingResult result){
		boolean nameHasSameVal = nameValidator3(customer);
		boolean phoneHasSameVal = phoneValidator3(customer);
		boolean emailHasSameVal = emailValidator3(customer);
		
		if(!phoneHasSameVal)
			result.rejectValue("phone", "HasSamePhoneError");
		if(!nameHasSameVal)
			result.rejectValue("nickname", "HasSameNameError");
		if(!emailHasSameVal)
			result.rejectValue("email", "HasSameEmailError");
		
		return nameHasSameVal && phoneHasSameVal && emailHasSameVal && validator_(customer,result);
	}
	
	private boolean emailValidator(Customer customer) {
		Pattern p2 = Pattern.compile("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+");
		Matcher m2 = p2.matcher(customer.getEmail());  
		boolean emailVal = m2.matches(); //email格式
		return emailVal;
	}
	
	private boolean emailValidator2(Customer customer) {
		return !(customerDAOimpl.hasSame(CustomerAttributeType.EMAIL,customer.getEmail()));
	}
	
	private boolean emailValidator3(Customer customer) {
		return !(customerDAOimpl.hasSame(CustomerAttributeType.EMAIL,customer.getEmail(),customer.getCustomer_id()));
	}

	private boolean nameValidator1(Customer customer) {
		int nameLen = customer.getNickname().length();
		boolean nameVal = (nameLen <= 14);  //用户名在14位内 
		return nameVal;
	}
	
	private boolean nameValidator2(Customer customer) {
		return !(customerDAOimpl.hasSame(CustomerAttributeType.NICKNAME,customer.getNickname()));
	}
	
	private boolean nameValidator3(Customer customer) {
		return !(customerDAOimpl.hasSame(CustomerAttributeType.NICKNAME,customer.getNickname(),customer.getCustomer_id()));
	}

	private boolean pwdValidator(Customer customer) {
		int pwdLen = customer.getPwd().length();
		boolean pwdVal = (pwdLen >= 6) && (pwdLen<= 14) ; //密码长度应在6-14位
		return pwdVal;
	}

	private boolean phoneValidator(Customer customer) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
		Matcher m = p.matcher(customer.getPhone());  
		boolean phoneVal = m.matches(); //手机格式
		return phoneVal;
	}
	
	private boolean phoneValidator2(Customer customer) {
		return !(customerDAOimpl.hasSame(CustomerAttributeType.PHONE,customer.getPhone()));
	}
	
	private boolean phoneValidator3(Customer customer) {
		return !(customerDAOimpl.hasSame(CustomerAttributeType.PHONE,customer.getPhone(),customer.getCustomer_id()));
	}
	
	
	public boolean addCompanyMember(CompanyMember companyMember){
		return customerDAOimpl.addCompanyMember(companyMember);
	}

	public CompanyMember getCompanyMemberByCID(int customerID){
		return customerDAOimpl.getCompanyMemberByCID(customerID);
	}

}
