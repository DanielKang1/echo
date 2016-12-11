package com.echo.service.evaluationservice;

import java.util.List;

import com.echo.domain.po.Evaluation;

public interface EvaluationService {
	
	/**
	 * 生成评论信息
	 * @param evaluation
	 * @return
	 */
	public boolean generateEva(Evaluation evaluation);
	
	/**
	 * 按照评论ID删除评论
	 * @param EvaluationID
	 * @return
	 */
	public boolean deleteEva(int EvaluationID);
	
	/**
	 * 获取酒店的所有评论
	 * @param hotelID
	 * @return
	 */
	public List<Evaluation> getHotelEva(int hotelID);
	
	/**
	 * 获取用户的所有评论
	 * @param hotelID
	 * @return
	 */
	public List<Evaluation> getCusEva(int customerID);

}
