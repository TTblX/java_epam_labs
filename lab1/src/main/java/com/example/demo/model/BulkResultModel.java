package com.example.demo.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BulkResultModel {
    private List<ExtendedResultModel> resultList;
    public BulkResultModel() {
        resultList = new CopyOnWriteArrayList<>();
    }

    public List<ExtendedResultModel> getResultList() {
        return resultList;
    }

    public void setResultList(List<ExtendedResultModel> resultList) {
        this.resultList = resultList;
    }
}
