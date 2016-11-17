package com.echo.domain.type;

public class OrderStatusType {
	
	public static final byte UNEXECUTED = 0;  //未执行
	
	public static final byte EXECUTED = 1;  //已正常执行结束（未评价）
	
	public static final byte CANCELLED = 2;  //已撤销订单
	
	public static final byte ABNORMAL = 3;  //异常订单
	
	public static final byte EVALUATED = 4;  //已评价（正常执行）订单
}
