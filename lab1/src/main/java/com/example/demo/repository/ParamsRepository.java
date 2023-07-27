package com.example.demo.repository;

import com.example.demo.model.ParamsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParamsRepository extends JpaRepository<ParamsModel, Integer> {
}
