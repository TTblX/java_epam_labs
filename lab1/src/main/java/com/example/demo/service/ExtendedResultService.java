package com.example.demo.service;

import com.example.demo.Cache.CacheModel;
import com.example.demo.model.ExtendedResultModel;
import com.example.demo.model.ParamsModel;
import com.example.demo.model.ResultModel;
import com.example.demo.repository.ExtendedResultServiceRepository;
import com.example.demo.repository.ParamsServiceRepository;
import com.example.demo.repository.ResultServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Scope("singleton")
public class ExtendedResultService {

    @Autowired
    private ExceptionService exception;
    @Autowired
    private CacheModel<ParamsModel, ExtendedResultModel> extendedCache;

    @Autowired
    private ParamsServiceRepository paramsServiceRepository;
    @Autowired
    private ResultServiceRepository resultServiceRepository;

    @Autowired
    private ExtendedResultServiceRepository extendedResultServiceRepository;

    public ExtendedResultService(){};
    public ExtendedResultModel getExtendedResult(ParamsModel params) {
        exception.checkExceptions(params);

        var cacheValue = extendedCache.Get(params);
        if(cacheValue != null) {
            return cacheValue;
        }

        paramsServiceRepository.save(params);

        ExtendedResultModel result = new ExtendedResultModel();

        params.getSource().chars().forEach(value -> {
            if (value == params.getTarget()) {
                result.setResult(result.getResult() + 1);
            }
        });

        result.setMinValue((char) params.getSource().chars().min().getAsInt());
        result.setMaxValue((char) params.getSource().chars().max().getAsInt());

        result.setSum( params.getSource().chars().sum());

        extendedResultServiceRepository.saveResult(result);
        extendedCache.push(params, result);

        return result;
    }
}
