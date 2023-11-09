package com.tekion.springbootelasticsearch.controller;

import com.tekion.springbootelasticsearch.mongo.service.EmployeeMongoService;
import com.tekion.springbootelasticsearch.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mongo")
@RequiredArgsConstructor
public class EmployeeMongoController {

    private final EmployeeMongoService employeeMongoService;

    @GetMapping("employee/{id}")
    public ResponseEntity<SuccessResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(SuccessResponse.builder().data(employeeMongoService.findByMongoId(id)).build());
    }

    @GetMapping("employees")
    public ResponseEntity<SuccessResponse> findAll() {
        return ResponseEntity.ok().body(SuccessResponse.builder().data(employeeMongoService.findAllMongoDocuments()).build());
    }
}
