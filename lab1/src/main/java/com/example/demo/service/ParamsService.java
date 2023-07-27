package com.example.demo.service;

import com.example.demo.Cache.CacheModel;
import com.example.demo.model.BulkResultModel;
import com.example.demo.model.ExtendedResultModel;
import com.example.demo.model.ParamsModel;
import com.example.demo.model.ResultModel;
import com.example.demo.repository.ExtendedResultServiceRepository;
import com.example.demo.repository.ParamsServiceRepository;
import com.example.demo.repository.ResultServiceRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ParamsService {

    @Autowired
    private CacheModel<ParamsModel, ResultModel> cache;

    @Autowired
    ExceptionService exception;

    @Autowired
    private ExtendedResultService extendedResultService;
    @Autowired
    private ParamsServiceRepository paramsServiceRepository;
    @Autowired
    private ResultServiceRepository resultServiceRepository;

    public ParamsService() {
    }

    public ResultModel getResult(ParamsModel params) {
        exception.checkExceptions(params);

        paramsServiceRepository.save(params);


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

        resultServiceRepository.saveResult(result);

        return result;
    }

    public BulkResultModel processBulkParams(List<ParamsModel> listOfParams) {
        BulkResultModel bulkResult = new BulkResultModel();
        List<ExtendedResultModel> resultList = new CopyOnWriteArrayList<>();


        listOfParams.parallelStream().forEach(params -> {

            resultList.add(extendedResultService.getExtendedResult(params));
        });

        bulkResult.setResultList(resultList);
        return bulkResult;
    }
}
