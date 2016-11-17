package com.echo.dao.test;

import com.echo.dao.evaluationdao.EvaluationDAO;
import com.echo.domain.po.Evaluation;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class EvaluationDAO_driver {
    public void drive(EvaluationDAO evaluationDAO) {
        evaluationDAO.get(0);
        evaluationDAO.add(new Evaluation());
        evaluationDAO.delete(0);

    }

    public void main(String[] args){
        (new EvaluationDAO_driver()).drive(new EvaluationDAO_stub()) ;
    }
}
