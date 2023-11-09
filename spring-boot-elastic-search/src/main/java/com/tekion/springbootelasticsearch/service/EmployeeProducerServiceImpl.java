package com.tekion.springbootelasticsearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekion.springbootelasticsearch.constants.KafkaConstants;
import com.tekion.springbootelasticsearch.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeProducerServiceImpl implements EmployeeProducerService{

    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String,String> kafkaTemplate;

    /**
     * @param employeeDTO
     * @return
     */
    @Override
    public Object send(EmployeeDTO employeeDTO) {
        try {
            kafkaTemplate.send(KafkaConstants.TOPIC_NAME, objectMapper.writeValueAsString(employeeDTO));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return "Kafka Message Send Successfully";
    }
}
