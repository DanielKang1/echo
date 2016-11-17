package com.echo.dao.evaluationdao;

import java.util.List;

import com.echo.domain.po.Evaluation;

public interface EvaluationDAO {
	
	/**
	 * 添加评论
	 * @param evaluation
	 * @return
	 */
	public boolean add(Evaluation evaluation);
	
	/**
	 * 删除评论
	 * @param evaluationID
	 * @return
	 */
	public boolean delete(int evaluationID);
	
	/**
	 * 获取酒店的所有评论
	 * @param hotelID
	 * @return
	 */
	public List<Evaluation> get(int hotelID);
	
	 

}
