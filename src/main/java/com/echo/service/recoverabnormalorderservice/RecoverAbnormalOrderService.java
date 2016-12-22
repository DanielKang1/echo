package com.echo.service.recoverabnormalorderservice;

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

/**
 * 撤销异常订单涉及多个操作，项目开启的事务级别是Service，所以Controller中的这些操作单独做成了Service。
 */
@Service
public class RecoverAbnormalOrderService {
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	@Autowired
	private CreditServiceImpl creditServiceImpl;
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	public boolean recoverAbnormalOrder(int orderID,int type){
		boolean flag = true;	//所有操作是否成功
		Order order = orderServiceImpl.getOrderByID(orderID);
		double oldCredit = customerServiceImpl.getBasicInfo(order.getOrderID()).getCredit()+order.getTotal();
		if(order != null){
			Customer customer = customerServiceImpl.getBasicInfo(order.getCustomerID());  //相关用户
			CreditChangeItem item = new CreditChangeItem(order.getCustomerID(), order.getOrderID(), CreditOperationType.ABNORMAL_CANCELLED,
					order.getHotel().getHotelID(), order.getHotel().getHotelName(), oldCredit, order.getTotal());
			
			try {
				//1.更新订单状态
				orderServiceImpl.updateOrderStatus(order, OrderStatusType.CANCELLED);
				if(type == 1){
					item.setAmount(order.getTotal() / 2 );
				}
				//2.生成信用变化记录
				creditServiceImpl.generateItem(item);
				//3.修改用户信用值（包含了可能对用户级别的修改）
				customerServiceImpl.modifyCredit(customer, item.getAmount());
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		
		return flag;
	}

}
