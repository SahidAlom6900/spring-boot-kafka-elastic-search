package com.tekion.springbootelasticsearch.controller;

import com.tekion.springbootelasticsearch.dto.EmployeeDTO;
import com.tekion.springbootelasticsearch.response.SuccessResponse;
import com.tekion.springbootelasticsearch.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("employee")
    public ResponseEntity<SuccessResponse> create(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok().body(SuccessResponse.builder().data(employeeService.create(employeeDTO)).build());
    }

    @GetMapping("employee/{id}")
    public ResponseEntity<SuccessResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(SuccessResponse.builder().data(employeeService.findById(id)).build());
    }

    @GetMapping("employees")
    public ResponseEntity<SuccessResponse> findAll() {
        return ResponseEntity.ok().body(SuccessResponse.builder().data(employeeService.findAll()).build());
    }

    @PutMapping("employee/{id}")
    public ResponseEntity<SuccessResponse> update(@PathVariable String id, @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok().body(SuccessResponse.builder().data(employeeService.update(id, employeeDTO)).build());
    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity<SuccessResponse> delete(@PathVariable String id) {
        employeeService.delete(id);
        return ResponseEntity.ok().body(SuccessResponse.builder().message("Delete Successfully With Employee Id " + id).build());
    }

    @GetMapping("json")
    public ResponseEntity<SuccessResponse> jsonDiff() {
        return ResponseEntity.ok().body(SuccessResponse.builder().message("Json Difference").data(employeeService.jsonDiff()).build());
    }
}
