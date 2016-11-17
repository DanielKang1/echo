package com.echo.dao.test;

import java.util.List;

import com.echo.dao.evaluationdao.EvaluationDAO;
import com.echo.domain.po.Evaluation;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class EvaluationDAO_stub implements EvaluationDAO {
    public boolean add(Evaluation evaluation) {
        System.out.println("add successfully");
        return false;
    }

    public boolean delete(int evaluationID) {
        System.out.println("delete successfully");
        return false;
    }

    public List<Evaluation> get(int hotelID) {
        System.out.println("show evaluation");
        return null;
    }
}
