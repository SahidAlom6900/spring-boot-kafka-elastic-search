package com.tekion.springbootelasticsearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekion.springbootelasticsearch.constants.KafkaConstants;
import com.tekion.springbootelasticsearch.dto.EmployeeDTO;
import com.tekion.springbootelasticsearch.entity.Employee;
import com.tekion.springbootelasticsearch.mongo.repository.EmployeeMongoRepository;
import com.tekion.springbootelasticsearch.repository.EmployeeElasticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeConsumerServiceImpl implements EmployeeConsumerService{

    private final EmployeeMongoRepository employeeMongoRepository;
    private final EmployeeElasticRepository employeeElasticRepository;

    private final ObjectMapper objectMapper;
    @KafkaListener(topics = KafkaConstants.TOPIC_NAME,
            groupId = KafkaConstants.GROUP_ID)
    public void consume(String message){
        try {
            EmployeeDTO employee = objectMapper.readValue(message, EmployeeDTO.class);
            Employee employeeElastic = new Employee();
            com.tekion.springbootelasticsearch.mongo.entity.Employee employeeMongo = new com.tekion.springbootelasticsearch.mongo.entity.Employee();
            BeanUtils.copyProperties(employee,employeeMongo);
            BeanUtils.copyProperties(employee,employeeElastic);
            employeeMongoRepository.save(employeeMongo);
            employeeElasticRepository.save(employeeElastic);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
