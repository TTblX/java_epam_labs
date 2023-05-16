package com.example.demo.service;

import com.example.demo.Cache.CacheModel;
import com.example.demo.model.ParamsModel;
import com.example.demo.model.ResultModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;

@Service
public class ParamsService {

    @Autowired
    private CacheModel<ParamsModel, ResultModel> cache;

    @Autowired
    ExceptionService exception;

    public ParamsService() {
    }

    public ResultModel getResult(ParamsModel params) {
        exception.checkExceptions(params);

        var cacheValue = cache.Get(params);
        if(cacheValue != null) {
            return cacheValue;
        }

        ResultModel result = new ResultModel();
        params.getSource().chars().forEach(value -> {
            if (value == params.getTarget()) {
                result.setResult(result.getResult() + 1);
            }
        });

        cache.push(params, result);

        return result;
    }
}
