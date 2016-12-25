package com.echo.test.dao;
import static org.hamcrest.Matchers.*;
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

import com.echo.dao.evaluationdao.EvaluationDAOImpl;
import com.echo.domain.po.Evaluation;

/**
 * Evaluation DAO Test
 * @author lenovo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "WebContent")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath:applicationContext.xml"),
        @ContextConfiguration(name = "child", locations = "classpath:springmvc.xml")
})
public class EvaluationTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private EvaluationDAOImpl impl;
	
	@Test
	public void testAdd(){
		Evaluation evaluation = new Evaluation(3, "testCusName", 1, "豪华房", 5, "merit...", "demerit...", "comment...", new Date());
		assertTrue(impl.add(evaluation));
	}
	
	@Test
	public void testDelete(){
		assertTrue(impl.delete(10032514));
	}
	
	@Test
	public void testQuery(){
		assertThat(impl.getByHotelID(1).size(),greaterThan(0));
		assertThat(impl.getByCusID(3).size(),greaterThan(0));
	}

}
