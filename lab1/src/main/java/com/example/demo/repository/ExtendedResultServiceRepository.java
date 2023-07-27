package com.example.demo.repository;

import com.example.demo.model.ExtendedResultModel;
import com.example.demo.model.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public class ExtendedResultServiceRepository {

    @Autowired
    private ExtendedResultRepository resultRepository;

    ExtendedResultServiceRepository(){};

    public void saveResult(ExtendedResultModel result) {
        resultRepository.save(result);
    }
}
