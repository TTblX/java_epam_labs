package com.example.demo.controller;

import com.example.demo.model.ParamsModel;
import com.example.demo.model.ResultModel;
import com.example.demo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParamsController {

    private final Logger logger = LoggerFactory.getLogger(ParamsController.class);

    @Autowired
    private AccessCounterService counter;

    @Autowired
    private ParamsService paramsService;

    @GetMapping("/counting")
    public ResultModel counting(@ModelAttribute ParamsModel params){
        counter.add();
        return paramsService.getResult(params);
    }

}
