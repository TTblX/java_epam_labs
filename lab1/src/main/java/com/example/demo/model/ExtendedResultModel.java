package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "extendedResult")
public class ExtendedResultModel extends ResultModel{
    @Getter
    @Setter
    private char maxValue;

    @Getter
    @Setter
    private char minValue;

    @Getter
    @Setter
    private int sum;
}
