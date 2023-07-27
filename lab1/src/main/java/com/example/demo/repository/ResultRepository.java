package com.example.demo.repository;

import com.example.demo.model.ResultModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<ResultModel, Integer> {
}
