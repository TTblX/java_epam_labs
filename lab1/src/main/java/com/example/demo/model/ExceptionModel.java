package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

public class ExceptionModel {

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private HttpStatusCode error;
}
