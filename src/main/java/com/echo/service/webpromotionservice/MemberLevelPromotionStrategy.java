package com.echo.service.webpromotionservice;

public class MemberLevelPromotionStrategy  implements WebPromotionStrategy {
	
	@Override
	public double getDiscount(WebPromotionHandleContext ctx) {
		return ctx.getMemberDiscount().getDiscount();
	}

}
