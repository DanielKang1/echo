package com.echo.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.echo.dao.webpromotiondao.WebPromotionDAOImpl;
import com.echo.domain.po.DistrictDiscount;
import com.echo.domain.po.MemberDiscount;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "WebContent")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath:applicationContext.xml"),
        @ContextConfiguration(name = "child", locations = "classpath:springmvc.xml")
})
public class WebPromotionTest {
	
	@Autowired
	public WebPromotionDAOImpl webPromotionImpl;
	
	@Test
	public void testAddMemberDiscount(){
		MemberDiscount md = new MemberDiscount();
		md.setCreditLimit(2222);
		md.setDiscount(0.88);
		md.setLevelID(7);
		md.setLevelName("v7");
		webPromotionImpl.add(md);
	}
	
	@Test
	public void testGetMemberDiscount(){
		System.out.println(webPromotionImpl.getMemberDiscount(0));
	}
	
	@Test
	public void testDeleteMemberDiscount(){
		System.out.println(webPromotionImpl.deleteMemberDiscount(7));
		
	}
	
	@Test
	public void testUpdateMemberDiscount(){
		MemberDiscount md = new MemberDiscount();
		md.setCreditLimit(3333);
		md.setLevelID(7);
		md.setDiscount(0.88);
		md.setLevelName("v7");
		webPromotionImpl.update(md);
	}
	
	@Test
	public void testMemberDiscountByCredit(){
		System.out.println(webPromotionImpl.getMemberDiscountByCredit(100));
	}
	
	@Test
	public void testAddDistrictDiscount(){
		DistrictDiscount dd = new DistrictDiscount();
		dd.setDistrictName("南京");
		dd.setDistrictID(1);
		webPromotionImpl.add(dd);
	}
	
	@Test
	public void testUpdateDistrictDiscount(){
		DistrictDiscount dd = new DistrictDiscount();
		dd.setId(1);
		dd.setDistrictName("南京");
		dd.setDistrictID(1);
		dd.setV1Discount(1);
		webPromotionImpl.update(dd);
	}
	
	@Test
	public void testDeleteDistrictDiscount(){
		System.out.println(webPromotionImpl.deleteDistrictDiscount(1));
	}
	
	@Test
	public void testGetAll(){
		System.out.println(webPromotionImpl.getAll());
	}
	
	@Test
	public void testGetDistrictDiscount(){
		System.out.println(webPromotionImpl.getDistrictDiscount(1));
	}
	

}
