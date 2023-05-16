package com.example.demo.service;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class AccessCounterService {

    @Getter
    private int counter;
    public void add() {counter++;}
}
