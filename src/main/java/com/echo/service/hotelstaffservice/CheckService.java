package com.echo.service.hotelstaffservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.echo.domain.po.CreditChangeItem;
import com.echo.domain.po.Customer;
import com.echo.domain.po.Order;
import com.echo.domain.po.Room;
import com.echo.domain.type.CreditOperationType;
import com.echo.domain.type.OrderStatusType;
import com.echo.service.creditservice.CreditServiceImpl;
import com.echo.service.customerservice.CustomerServiceImpl;
import com.echo.service.orderservice.OrderServiceImpl;
import com.echo.service.roomservice.RoomServiceImpl;

/**
 * CheckService专门负责线上入住/退房的处理 
 * Spring开启的事务级别在Service，若单独在Controller上的方法加上事务注解不太优雅。
 * 为保证入住/退房处理的各种操作一起提交或回滚，便将这些操作单独封在一个Service里。
 */
@Service
public class CheckService {
	
	@Autowired
	public RoomServiceImpl roomServiceImpl;
	@Autowired
	public OrderServiceImpl orderServicceImpl;
	@Autowired
	public CreditServiceImpl creditServiceImpl;
	@Autowired
	public CustomerServiceImpl customerServiceImpl;
	
	/**
	 * 对订单进行入住处理
	 * @param order 待处理的订单
	 * @return 成功入住的房间号列表
	 */
	public String checkin(Order order,double oldCredit){
		//1.房间状态处理
		List<Room> selectBooked = roomServiceImpl.checkIn(order.getRoomType().getTypeID(),order.getBookingNum());
		//2.记录入住信息
		roomServiceImpl.addRoomCheckinItem(order, selectBooked);
		
		//3.添加信用处理记录
		//若执行的是异常订单，则恢复扣除的信用值
		if(order.getOrderType() == OrderStatusType.ABNORMAL){
			CreditChangeItem creditChangeItem0 = new CreditChangeItem(order.getCustomerID(),order.getOrderID(),CreditOperationType.ABNORMAL_EXECUTED,
					order.getHotel().getHotelID(),order.getHotel().getHotelName(),oldCredit,order.getTotal());
			creditServiceImpl.generateItem(creditChangeItem0);
		}
		if(order.getOrderType() == OrderStatusType.UNEXECUTED){
			CreditChangeItem creditChangeItem = new CreditChangeItem(order.getCustomerID(),order.getOrderID(),CreditOperationType.ORDER_EXECUTED,
					order.getHotel().getHotelID(),order.getHotel().getHotelName(),oldCredit,order.getTotal());
			creditServiceImpl.generateItem(creditChangeItem);
		}
		
		//4.修改用户信用记录值
		Customer targetCustomer = customerServiceImpl.getBasicInfo(order.getCustomerID());
		customerServiceImpl.modifyCredit(targetCustomer, order.getTotal());
		
		//5.订单状态处理
		orderServicceImpl.updateOrderStatus(order, OrderStatusType.EXECUTED);
		
		StringBuilder sb = new StringBuilder("入住的房间号是：");
		for(Room room : selectBooked){
			sb.append(room.getRoomNumber() +" ");
		}
		
		return sb.toString();
	}
	
	/**
	 * 退房处理（以房间为单位）
	 * @param roomCheckItemID
	 * @return
	 */
	public boolean checkout(int roomCheckItemID){
		//1.房间状态
		boolean flag1 = roomServiceImpl.checkOut(roomCheckItemID);
		//2.更新RoomCheckItem
		boolean flag2 = roomServiceImpl.updateRoomCheckinItem(roomCheckItemID);
		
		return flag1 && flag2;
	}

}
