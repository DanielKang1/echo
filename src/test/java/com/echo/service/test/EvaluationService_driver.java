package com.echo.service.test;

import com.echo.domain.po.Evaluation;
import com.echo.service.evaluationservice.EvaluationService;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class EvaluationService_driver {
    public void drive(EvaluationService evaluationService){
        evaluationService.deleteEva(0);
        evaluationService.generateEva(new Evaluation());
        evaluationService.getHotelEva(0);
    }

    public static void main(String[] args) {
        (new EvaluationService_driver()).drive(new EvaluationService_stub());
    }
}
