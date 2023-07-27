package com.example.demo.repository;

import com.example.demo.model.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public class ResultServiceRepository {
    @Autowired
    private ResultRepository resultRepository;

    ResultServiceRepository(){};

    public void saveResult(ResultModel result) {
        resultRepository.save(result);
    }
}
