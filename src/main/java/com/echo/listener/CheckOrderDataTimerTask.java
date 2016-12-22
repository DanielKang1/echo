package com.echo.listener;

import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.echo.service.checkorderdailytask.CheckOrderDailyTask;

@Component
public class CheckOrderDataTimerTask extends TimerTask {
	
	@Autowired
	private CheckOrderDailyTask checkOrderDailyTask;

	private static Logger log = Logger.getLogger(CheckOrderDataTimerTask.class);

	@Override
	public void run() {
		try {
			System.out.println(checkOrderDailyTask);
			log.info("-------------异常订单检验任务：开始--------------");
			checkOrderDailyTask.checkAbnormalOrders();
		} catch (Exception e) {
			System.out.println(e);
			log.info("-------------异常订单检验任务：执行异常--------------");
		}
	}
	
	
}