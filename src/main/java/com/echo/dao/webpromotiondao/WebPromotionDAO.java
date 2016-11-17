package com.echo.dao.webpromotiondao;

import java.util.List;

import com.echo.domain.po.DistrictDiscount;
import com.echo.domain.po.MemberDiscount;
import com.echo.domain.po.PromotionDate;

public interface WebPromotionDAO {
	
	/**
	 * 修改会员等级信息
	 * @param levelID
	 * @param discount
	 * @return
	 */
	public boolean update(MemberDiscount memberDiscount);
	
	/**
	 * 添加会员等级信息
	 * @param memberDiscount
	 * @return
	 */
	public boolean add(MemberDiscount memberDiscount);
	
	/**
	 * 删除会员等级信息
	 * @param memberDiscount
	 * @return
	 */
	public boolean deleteMemberDiscount(int  levelID);
	
	/**
	 * 按照等级ID获得MemberDiscount
	 * @param levelID
	 * @return
	 */
	public MemberDiscount getMemberDiscount(int levelID);
	
	/**
	 * 按照信用值获得MemberDiscount
	 * @param credit
	 * @return
	 */
	public MemberDiscount getMemberDiscountByCredit(double credit);
	
	
	/**
	 * 添加VIP会员特定商圈专属折扣
	 * @return
	 */
	public boolean add(DistrictDiscount districtDiscount);
	
	/**
	 * 更新VIP会员特定商圈专属折扣
	 * @param districtDiscount
	 * @return
	 */
	public boolean update(DistrictDiscount districtDiscount);
	
	/**
	 * 按照ID删除特定商圈专属折扣
	 * @param districtDiscountID
	 * @return
	 */
	public boolean deleteDistrictDiscount(int districtDiscountID);
	
	/**
	 * 按照ID获得特定商圈专属折扣
	 * @param districtDiscountID
	 * @return
	 */
	public DistrictDiscount getDistrictDiscount(int districtDiscountID);
	
	/**
	 * 按照地区名获得特定商圈专属折扣
	 * @param districtName
	 * @return
	 */
	public DistrictDiscount getDistrictDiscount(String districtName);
	
	/**
	 * 获得所有特定商圈折扣
	 * @return
	 */
	public List<DistrictDiscount> getAll();
	
	public List<PromotionDate> getPromotionDateList();
	
	public boolean updateDatesPromotion(PromotionDate promotionDate);
	
	public boolean deleteDatesPromotion(int id);
	
	public boolean addPromotionDates(PromotionDate promotionDate);
	

}
