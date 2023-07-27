package com.example.demo.service;

import com.example.demo.Cache.CacheModel;
import com.example.demo.model.ParamsModel;
import com.example.demo.model.ResultModel;
import com.example.demo.repository.ParamsServiceRepository;
import com.example.demo.repository.ResultServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Service
@Scope("singleton")
public class AsyncParamsService {
    @Autowired
    private ExceptionService exception;

    @Autowired
    private ParamsServiceRepository paramsServiceRepository;

    @Autowired
    private CacheModel<ParamsModel, ResultModel> cache;

    @Autowired
    private ResultServiceRepository resultServiceRepository;

    public AsyncParamsService() {};

    public CompletableFuture<ResultModel> getResultAsync(ParamsModel params) {
        CompletableFuture<ResultModel> future = new CompletableFuture<>();

        exception.checkExceptions(params);

        paramsServiceRepository.save(params);

        var cacheValue = cache.Get(params);
        if (cacheValue != null) {
            future.complete(cacheValue);
            return future;
        }

        CompletableFuture.supplyAsync(() -> {
            ResultModel result = new ResultModel();
            params.getSource().chars().forEach(value -> {
                if (value == params.getTarget()) {
                    result.setResult(result.getResult() + 1);
                }
            });
            cache.push(params, result);
            resultServiceRepository.saveResult(result);

            return result;
        }).exceptionally(ex -> {
            future.completeExceptionally(ex);
            return null;
        }).thenAcceptAsync(future::complete);

        return future;
    }
}
