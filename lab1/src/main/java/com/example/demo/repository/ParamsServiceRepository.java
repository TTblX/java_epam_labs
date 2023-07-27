package com.example.demo.repository;

import com.example.demo.model.ParamsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope("singleton")
public class ParamsServiceRepository {

    @Autowired
    private ParamsRepository paramsRepository;
    public ParamsModel read(int id) {
        return paramsRepository.findById(id).orElseThrow();
    }

    public void save(ParamsModel params) {
        paramsRepository.save(params);
    }

    public void delete(int id) {
        paramsRepository.deleteById(id);
    }

    public List<ParamsModel> getAll() {
        return paramsRepository.findAll();
    }
}
