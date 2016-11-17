package com.echo.service.webpromotionservice;

import org.springframework.beans.factory.annotation.Autowired;

import com.echo.domain.po.MemberDiscount;

public class MemberLevelPromotionStrategy  implements WebPromotionStrategy {
	
	@Autowired
	private WebPromotionServiceImpl webPromotionServiceImpl;

	@Override
	public double getDiscount(WebPromotionHandleContext ctx) {
		MemberDiscount  memberDiscount = webPromotionServiceImpl.getMemberDiscountByLevelID(ctx.getGrade());
		return memberDiscount.getDiscount();
	}

}
