package com.example.demo.repository;

import com.example.demo.model.ExtendedResultModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtendedResultRepository extends JpaRepository<ExtendedResultModel, Integer> {
}
