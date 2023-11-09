package com.tekion.springbootelasticsearch.repository;

import com.tekion.springbootelasticsearch.entity.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EmployeeElasticRepository extends ElasticsearchRepository<Employee, String> {
}
