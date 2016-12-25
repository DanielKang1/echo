package com.echo.test.service.mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import com.echo.dao.creditdao.CreditDAOImpl;
import com.echo.domain.po.CreditChangeItem;
import com.echo.domain.type.CreditOperationType;
import com.echo.service.creditservice.CreditServiceImpl;

public class CreditTest {
	
	@InjectMocks
	private CreditServiceImpl mockCreditServiceImpl;
	
	@Mock
	private CreditDAOImpl mockCreditDAOImpl;
	
	@Before
    public void initMocks() {
    	//初始化当前测试类所有Mock模拟对象
        MockitoAnnotations.initMocks(this);
    }
	
	/**
	 * 测试添加信用改变Item，并且按照订单号与用户ID进行获取
	 */
	@Test
	public void testAddAndGetCreditItem(){
		CreditChangeItem creditChangeItem1 = new CreditChangeItem(10010, 10024210, CreditOperationType.ORDER_ABNORMAL, 1, "南京金陵饭店", 1000,-344);
		CreditChangeItem creditChangeItem2 = new CreditChangeItem(10010, 10024210, CreditOperationType.ABNORMAL_CANCELLED, 1, "南京金陵饭店", 1000, +344);
		CreditChangeItem creditChangeItem3 = new CreditChangeItem(10010, 10024210, CreditOperationType.ORDER_EXECUTED, 1, "南京金陵饭店", 1000, 688);
		CreditChangeItem creditChangeItem4 = new CreditChangeItem(10010, 0, CreditOperationType.RECHARGE, 0, "", 1000, 1000);
		List<CreditChangeItem> items1 = new ArrayList<>();
		items1.add(creditChangeItem1);
		items1.add(creditChangeItem2);
		items1.add(creditChangeItem3);
		List<CreditChangeItem> items2 = new ArrayList<>();
		items2.add(creditChangeItem4);
		items2.addAll(items1);
		when(mockCreditDAOImpl.add(any(CreditChangeItem.class))).thenReturn(true);
		when(mockCreditDAOImpl.getByCID(10010)).thenReturn(items2);
		when(mockCreditDAOImpl.getByOID(10024210)).thenReturn(items1);
		
		assertTrue(mockCreditServiceImpl.generateItem(creditChangeItem1));
		assertTrue(mockCreditServiceImpl.generateItem(creditChangeItem2));
		assertTrue(mockCreditServiceImpl.generateItem(creditChangeItem3));
		assertTrue(mockCreditServiceImpl.generateItem(creditChangeItem4));
		assertThat(mockCreditServiceImpl.getCustomerItem(10010).size(),is(4));
		assertThat(mockCreditServiceImpl.getOrderItem(10024210).size(),is(3));
		
		verify(mockCreditDAOImpl,times(4)).add(any(CreditChangeItem.class));
		verify(mockCreditDAOImpl,times(1)).getByCID(any(int.class));
		verify(mockCreditDAOImpl,times(1)).getByOID(any(int.class));
	}
	
	

}
