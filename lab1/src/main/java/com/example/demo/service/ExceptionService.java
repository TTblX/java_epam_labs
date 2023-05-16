package com.example.demo.service;

import com.example.demo.model.ParamsModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
public class ExceptionService {
    ExceptionService() {}

    public void checkExceptions(ParamsModel params) throws InvalidParameterException, IllegalArgumentException
    {
        if (params.getSource() == null || params.getSource().length() == 0)
            throw new InvalidParameterException("source length must be above 0");


    }
}
