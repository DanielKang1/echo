package com.echo.service.checkOrderDailyTask;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.echo.domain.po.CreditChangeItem;
import com.echo.domain.po.Customer;
import com.echo.domain.po.Order;
import com.echo.domain.type.CreditOperationType;
import com.echo.domain.type.OrderStatusType;
import com.echo.service.creditservice.CreditServiceImpl;
import com.echo.service.customerservice.CustomerServiceImpl;
import com.echo.service.orderservice.OrderServiceImpl;

@Service
public class CheckOrderDailyTask {
	
	@Autowired
	private CreditServiceImpl creditServiceImpl;
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	@Autowired
	public OrderServiceImpl orderServiceImpl;
	
	/**
	 * 用于每日12点检查异常订单（轮循机制见CheckOrderDataTimerTask、TimerManager、CheckOrderDataTaskListener）
	 */
	public boolean checkAbnormalOrders(){
		List<Order> orders = orderServiceImpl.getUnexecutedOrders();
		for(Order order : orders){
			Date latestDate = order.getLatestDate();
			long latest = latestDate.getTime();
			long current = new Date().getTime();
			if(current >= latest){
				abnormalOrderHandle(order);
			}
		}
		return false;
	}

	/**
	 * 对异常订单进行处理
	 */
	private void abnormalOrderHandle(Order order) {
		//订单设置为异常
		orderServiceImpl.updateOrderStatus(order, OrderStatusType.ABNORMAL);
		
		//扣除用户信用值(1.添加Credit记录)
		CreditChangeItem creditChangeItem = new CreditChangeItem(order.getCustomerID(), order.getOrderID(), CreditOperationType.ORDER_ABNORMAL,
				order.getHotel().getHotelID(), order.getHotel().getHotelName(), -order.getTotal());
		creditServiceImpl.generateItem(creditChangeItem);
		
		//扣除用户信用值(2.更改用户属性)
		Customer customer = customerServiceImpl.getBasicInfo(order.getCustomerID());
		try {
			customerServiceImpl.modifyCredit(customer, -order.getTotal()); //等级的更新在modifyCredit中已实现
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
