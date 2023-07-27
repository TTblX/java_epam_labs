package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Entity
@Table(name = "params")
public class ParamsModel {

    @Getter
    @Setter
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Integer id;

    @Getter
    @Setter
    @Column(name = "source")
    private String source;

    @Getter
    @Setter
    @Column(name = "target")
    private char target;

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
