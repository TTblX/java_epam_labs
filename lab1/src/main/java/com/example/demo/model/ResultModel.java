package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "results")
public class ResultModel {

    @Getter
    @Setter
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Integer id;

    @Getter
    @Setter
    @Column(name = "result")
    private int result;
}
