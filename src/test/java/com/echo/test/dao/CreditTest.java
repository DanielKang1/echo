package com.echo.test.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.echo.dao.creditdao.CreditDAOImpl;
import com.echo.domain.po.CreditChangeItem;
import com.echo.domain.type.CreditOperationType;
/**
 * Credit DAO 测试
 * @author lenovo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "WebContent")
@ContextHierarchy({
        @ContextConfiguration(locations = "classpath:applicationContext.xml"),
})
public class CreditTest  extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private CreditDAOImpl creditDAOImpl;
	
	@Test
	public void testAdd(){
		CreditChangeItem creditChangeItem = new CreditChangeItem(3, 1043212, CreditOperationType.ORDER_EXECUTED, 1, "南京金陵饭店", 1000, 1000);
		assertTrue(creditDAOImpl.add(creditChangeItem));
	}
	
	@Test
	public void testgGetByCID(){
		CreditChangeItem creditChangeItem = new CreditChangeItem(34, 1043212, CreditOperationType.ORDER_EXECUTED, 1, "南京金陵饭店", 1000, 1000);
		creditDAOImpl.add(creditChangeItem);
		assertThat(creditDAOImpl.getByCID(34).size(),greaterThan(0));
	}
	
	@Test
	public void testGetByOID(){
		CreditChangeItem creditChangeItem = new CreditChangeItem(3, 1043212, CreditOperationType.ORDER_EXECUTED, 1, "南京金陵饭店", 1000, 1000);
		creditDAOImpl.add(creditChangeItem);
		assertThat(creditDAOImpl.getByOID(1043212).size(),greaterThan(0));
	}
	

}
