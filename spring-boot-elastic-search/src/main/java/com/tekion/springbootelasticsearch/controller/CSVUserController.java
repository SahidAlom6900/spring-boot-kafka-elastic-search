package com.tekion.springbootelasticsearch.controller;

import com.tekion.springbootelasticsearch.dto.EmployeeDTO;
import com.tekion.springbootelasticsearch.response.SuccessResponse;
import com.tekion.springbootelasticsearch.service.CSVService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/csv")
@RequiredArgsConstructor
public class CSVUserController {
    private final CSVService csvService;

    @PostMapping
    public ResponseEntity<SuccessResponse> create(@RequestPart(name = "file")MultipartFile multipartFile) {
        return ResponseEntity.ok().body(SuccessResponse.builder().data(csvService.saveData(multipartFile)).build());
    }
}
