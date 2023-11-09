package com.tekion.springbootelasticsearch.service;

import com.tekion.springbootelasticsearch.dto.EmployeeDTO;

public interface EmployeeProducerService {
    Object send(EmployeeDTO employeeDTO);
}
