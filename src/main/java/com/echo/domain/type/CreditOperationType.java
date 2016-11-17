package com.echo.domain.type;

public class CreditOperationType {
	
	public static final byte ORDER_EXECUTED = 1;  //订单执行+
	
	public static final byte ORDER_ABNORMAL = 2;  //订单异常-
	
	public static final byte ABNORMAL_CANCELLED = 3; //异常订单取消 +
	
	public static final byte RECHARGE = 4; //充值 +

}
