package com.tekion.springbootelasticsearch.mongo.service;

import com.tekion.springbootelasticsearch.mongo.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeMongoService {

    public Optional<Employee> findByMongoId(String id);

    public List<Employee> findAllMongoDocuments();
}
