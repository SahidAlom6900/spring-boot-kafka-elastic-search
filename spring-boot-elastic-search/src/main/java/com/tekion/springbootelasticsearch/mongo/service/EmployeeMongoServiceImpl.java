package com.tekion.springbootelasticsearch.mongo.service;

import com.tekion.springbootelasticsearch.mongo.entity.Employee;
import com.tekion.springbootelasticsearch.mongo.repository.EmployeeMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeMongoServiceImpl implements EmployeeMongoService {

    private final EmployeeMongoRepository employeeMongoRepository;

    private final ElasticsearchOperations elasticsearchOperations;

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Employee> findByMongoId(String id) {
        return employeeMongoRepository.findById(id);
    }

    /**
     */
    @Override
    public List<Employee> findAllMongoDocuments() {
        return new ArrayList<>(employeeMongoRepository.findAll());
    }
}
