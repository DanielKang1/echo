package com.echo.listener;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimerManager {
	
	@Autowired
	private CheckOrderDataTimerTask checkOrderDataTimerTask;

	// 时间间隔
	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
	
	public void start(){
		Calendar calendar = Calendar.getInstance();

		/*** 定制每日0:00执行方法 ***/

		calendar.set(Calendar.HOUR_OF_DAY, 24);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);

		Date date = calendar.getTime(); // 第一次执行定时任务的时间

		// 如果第一次执行定时任务的时间 小于 当前的时间
		// 此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
		if (date.before(new Date())) {
			date = this.addDay(date, 1);
		}

		Timer timer = new Timer();
		// 安排指定的任务在指定的时间开始进行重复的固定延迟执行。
		timer.schedule(checkOrderDataTimerTask, date, PERIOD_DAY);
	}

	// 增加或减少天数
	public Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}

}

// 在 TimerManager 这个类里面，一定要注意 时间点的问题。如果你设定在凌晨2点执行任务。但你是在2点以后
// 发布的程序或是重启过服务，那这样的情况下，任务会立即执行，而不是等到第二天的凌晨2点执行。为了，避免这种情况
// 发生，只能判断一下，如果发布或重启服务的时间晚于定时执行任务的时间，就在此基础上加一天。
