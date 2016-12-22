package com.echo.service.evaluationservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.echo.dao.evaluationdao.EvaluationDAOImpl;
import com.echo.domain.po.Evaluation;

@Service
public class EvaluationServiceImpl implements EvaluationService{
	
	@Autowired
	private EvaluationDAOImpl evaluationDAOImpl;

	@Override
	public boolean generateEva(Evaluation evaluation) {
		return evaluationDAOImpl.add(evaluation);
	}

	@Override
	public boolean deleteEva(int EvaluationID) {
		return evaluationDAOImpl.delete(EvaluationID);
	}

	@Override
	public List<Evaluation> getHotelEva(int hotelID) {
		return evaluationDAOImpl.getByHotelID(hotelID);
	}

	@Override
	public List<Evaluation> getCusEva(int customerID) {
		return evaluationDAOImpl.getByCusID(customerID);
	}
	
	//获得该酒店平均得分
	public double getAverageRating(int hotelID){
		double sum = 0;
		List<Evaluation> evas = getHotelEva(hotelID);
		if(evas.size() == 0){
			return 0;
		}
		else{
			for(Evaluation eva : evas){
				sum += eva.getMark();
			}
			String res = String.format("%.1f",sum / evas.size());
			return Double.parseDouble(res);
		}
	}

}
