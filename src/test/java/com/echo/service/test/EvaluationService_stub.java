package com.echo.service.test;

import java.util.List;

import com.echo.domain.po.Evaluation;
import com.echo.service.evaluationservice.EvaluationService;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class EvaluationService_stub implements EvaluationService {
    public boolean generateEva(Evaluation evaluation) {
        System.out.println("generateEva successfully");
        return false;
    }

    public boolean deleteEva(int EvaluationID) {
        System.out.println("deleteEva successfully");
        return false;
    }

    public List<Evaluation> getHotelEva(int hotelID) {
        System.out.println("show Evaluation");
        return null;
    }

	@Override
	public List<Evaluation> getCusEva(int customerID) {
		 System.out.println("show getCusEva");
		return null;
	}
}
