package com.tekion.springbootelasticsearch.mongo.repository;

import com.tekion.springbootelasticsearch.mongo.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeMongoRepository extends MongoRepository<Employee,String> {
}
