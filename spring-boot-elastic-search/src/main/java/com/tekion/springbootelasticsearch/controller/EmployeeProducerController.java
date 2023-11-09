package com.tekion.springbootelasticsearch.controller;

import com.tekion.springbootelasticsearch.dto.EmployeeDTO;
import com.tekion.springbootelasticsearch.response.SuccessResponse;
import com.tekion.springbootelasticsearch.service.EmployeeProducerService;
import com.tekion.springbootelasticsearch.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EmployeeProducerController {

    private final EmployeeProducerService employeeProducerService;

    @PostMapping("send")
    public ResponseEntity<SuccessResponse> send(@RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok().body(SuccessResponse.builder().error(false).message("Send Message Successfully").data(employeeProducerService.send(employeeDTO)).build());
    }
}
