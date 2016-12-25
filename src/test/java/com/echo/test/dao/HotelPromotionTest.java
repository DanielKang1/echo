package com.echo.test.dao;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.echo.dao.hotelpromotiondao.HotelPromotionDAOImpl;
import com.echo.domain.po.HotelPromotionItem;
import com.echo.domain.po.PromotionDate;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "WebContent")
@ContextHierarchy({
        @ContextConfiguration( locations = "classpath:applicationContext.xml"),
})
public class HotelPromotionTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private HotelPromotionDAOImpl impl;
	
	@Test
	public void testAddPromotionDate(){
		PromotionDate promotionDate = new PromotionDate(1, new Date(), new Date(), 0.9);
		assertTrue(impl.add(promotionDate));
	}
	
	@Test
	public void testAddHotelPromotionItem(){
		HotelPromotionItem item = new HotelPromotionItem(1, 1, false, 3, 1, false, 1, false);
		assertTrue(impl.add(item));
	}
	
	@Test
	public void testDeleteHotelPromotionItem(){
		assertTrue(impl.deleteDatesPromotion(1));
	}
	
	@Test
	public void testGetPromotionDateList(){
		assertThat(impl.getPromotionDateList(1).size(),greaterThan(0));
	}
	
	@Test
	public void testGetHotelPromotionItem(){
		assertNotNull(impl.getHotelPromotionItem(1));
	}
		

}
