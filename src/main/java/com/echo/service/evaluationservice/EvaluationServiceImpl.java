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

}
