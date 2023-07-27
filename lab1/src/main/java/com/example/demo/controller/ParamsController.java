package com.example.demo.controller;

import com.example.demo.model.BulkResultModel;
import com.example.demo.model.ExtendedResultModel;
import com.example.demo.model.ParamsModel;
import com.example.demo.model.ResultModel;
import com.example.demo.repository.ParamsServiceRepository;
import com.example.demo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class ParamsController {

    private final Logger logger = LoggerFactory.getLogger(ParamsController.class);

    @Autowired
    private AccessCounterService counter;

    @Autowired
    private ParamsService operationsService;

    @Autowired
    private ExtendedResultService extendedResultService;

    @Autowired
    private ParamsServiceRepository paramsServiceRepository;

    @Autowired
    private AsyncParamsService asyncParamsService;

    @GetMapping("/counting")
    public ResultModel counting(@ModelAttribute ParamsModel params){
        counter.add();
        return operationsService.getResult(params);
    }

    @GetMapping("/extendedResult")
    public ExtendedResultModel extendedResult(@ModelAttribute ParamsModel params)
    {
        counter.add();
        return extendedResultService.getExtendedResult(params);
    }

    @PostMapping("/bulk")
    public BulkResultModel processBulkParams(@RequestBody List<ParamsModel> paramsList) {
        counter.add();
        return  operationsService.processBulkParams(paramsList);
    }

    @RequestMapping(value = {"/params/{id}", "/async/result/{id}"})
    public ParamsModel getParamsFromDataBase(@PathVariable Integer id) {
        counter.add();
        return paramsServiceRepository.read(id);
    }

    @GetMapping("/getAllRecords")
    public List<ParamsModel> getAllRecordsFromDataBase() {
        counter.add();
        return paramsServiceRepository.getAll();
    }

    @DeleteMapping("/deleteRecord/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        counter.add();
        paramsServiceRepository.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/async/result")
    public ResponseEntity<String> getResultAsync(@RequestBody ParamsModel params) {
        CompletableFuture<ResultModel> future = asyncParamsService.getResultAsync(params);
        // Возвращаем HTTP статус 202 Accepted и заголовок с URL для проверки статуса
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(params.getId())
                .toUri());
        return new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
    }
}
