package com.echo.test.mock;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.echo.dao.customerdao.CustomerDAOimpl;
import com.echo.dao.webpromotiondao.WebPromotionDAOImpl;
import com.echo.domain.po.Customer;
import com.echo.domain.po.MemberDiscount;
import com.echo.service.customerservice.CustomerServiceImpl;

public class CustomerTest {
	
	@Mock  
	private CustomerDAOimpl mockCustomerDAOimpl;    //模拟CustomerDAOimpl实现类
	
	@Mock
	public WebPromotionDAOImpl mockWebPromotionDAOImpl; //模拟WebPromotionDAOImpl实现类
	
	@InjectMocks  
	private CustomerServiceImpl mockCustomerServiceImpl; //模拟CustomerServiceImpl实现类
	
	private Customer mockCustomer = new Customer("Finch","123@123.com","123456","13412341234",234) ;
	
    @Before 
    public void initMocks() {
    	//初始化当前测试类所有Mock模拟对象
        MockitoAnnotations.initMocks(this);
    }
    
    /*
     * 测试登录
     */
    @Test
    public void testLogin() {
    	when(mockCustomerDAOimpl.get("Finch","123456")).thenReturn(mockCustomer);
    	assertNotNull(mockCustomerServiceImpl.login("Finch","123456"));
    	verify(mockCustomerDAOimpl).get(anyString(),anyString());
    }
    
    /**
     * 测试注册
     */
    @Test
    public void testRegister(){
    	when(mockCustomerDAOimpl.add(mockCustomer)).thenReturn(true);
    	assertTrue(mockCustomerServiceImpl.register(mockCustomer));
    	verify(mockCustomerDAOimpl).add(any(Customer.class));
    }
    
    /**
     * 测试修改客户信息与密码
     */
    @Test
    public void testModifyInfoAndPwd(){
    	when(mockCustomerDAOimpl.update(mockCustomer)).thenReturn(true);
    	assertTrue(mockCustomerServiceImpl.modifyInfo(mockCustomer));
    	System.out.println("-------------modifyInfo-------------");
    	System.out.println(mockCustomer); 
    	assertTrue(mockCustomerServiceImpl.modifyPwd(mockCustomer,"654321"));
    	System.out.println("-------------modifyPwd-------------");
    	System.out.println(mockCustomer); 
    	verify(mockCustomerDAOimpl,times(2)).update(any(Customer.class));
    }
    
    /**
     * 测试成为会员
     */
//    @Test
//    public void testBeMember(){
//    	MemberDiscount md = new MemberDiscount();
//    	md.setLevelID(2);
//    	when(mockWebPromotionDAOImpl.getMemberDiscountByCredit(234)).thenReturn(md);
//    	when(mockCustomerDAOimpl.update(mockCustomer)).thenReturn(true);
//    	
//    	assertTrue(mockCustomerServiceImpl.beMember(mockCustomer));
//    	System.out.println("-------------beMember-------------");
//    	System.out.println(mockCustomer.getGrade()); 
//    	
//    	verify(mockWebPromotionDAOImpl,times(1)).getMemberDiscountByCredit(any(double.class));
//    	verify(mockCustomerDAOimpl,times(1)).update(any(Customer.class));
//    }
    
    /**
     * 测试成为VIP会员
     */
//    @Test
//    public void testBeVIPMember(){
//    	MemberDiscount md = new MemberDiscount();
//    	md.setLevelID(2);
//    	when(mockWebPromotionDAOImpl.getMemberDiscountByCredit(234)).thenReturn(md);
//    	when(mockCustomerDAOimpl.update(mockCustomer)).thenReturn(true);
//    	
//    	assertTrue(mockCustomerServiceImpl.beVIPMember(mockCustomer));
//    	System.out.println("-------------beVIPMember-------------");
//    	System.out.println(mockCustomer.getGrade());
//    	
//    	verify(mockWebPromotionDAOImpl,times(1)).getMemberDiscountByCredit(any(double.class));
//    	verify(mockCustomerDAOimpl,times(1)).update(any(Customer.class));
//    }
    
    /**
     * 测试在对信用值进行修改时，重新获得用户的信息
     */
    @Test
    public void testModifyCredit(){
    	MemberDiscount md = new MemberDiscount();
    	md.setLevelID(3);
    	
    	when(mockWebPromotionDAOImpl.getMemberDiscountByCredit(1234)).thenReturn(md);
    	when(mockCustomerDAOimpl.update(mockCustomer)).thenReturn(true);
    	assertTrue(mockCustomerServiceImpl.modifyCredit(mockCustomer,1000));
    	System.out.println("-------------ModifyCredit-------------");
    	System.out.println(mockCustomer.getGrade());
    	
    	verify(mockWebPromotionDAOImpl,times(1)).getMemberDiscountByCredit(any(double.class));
    	verify(mockCustomerDAOimpl,times(1)).update(any(Customer.class));
    }
    
    /**
     * 测试客户进行加密与解密
     */
    @Test
    public void testEncodeAndDecodeCustomer(){
    	mockCustomerServiceImpl.encodeCustomer(mockCustomer);
    	System.out.println("-------------EncodeCustomer-------------");
    	System.out.println(mockCustomer);
    	
    	mockCustomerServiceImpl.decodeCustomer(mockCustomer);
    	System.out.println("-------------DecodeCustomer-------------");
    	System.out.println(mockCustomer);
    }
    
    

}
