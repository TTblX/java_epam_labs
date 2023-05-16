package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


public class ParamsModel {

    @Getter
    @Setter
    private String source;

    @Getter
    @Setter
    private char target;

    private ResultModel resultModel;

    public ParamsModel() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParamsModel that = (ParamsModel) o;
        return target == that.target && source.equals(that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target);
    }
}
