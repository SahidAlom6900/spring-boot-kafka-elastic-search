package com.tekion.springbootelasticsearch.mongo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tekion.springbootelasticsearch.entity.Address;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@org.springframework.data.mongodb.core.mapping.Document(collection = "employee")
public class Employee {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING, timezone = "Asia/Kolkata")
    private LocalDate doj;
    private BigDecimal salary;
    private Boolean isActive;
    private Boolean type;
    private Long mobileNo;
    private List<Address> addresses;
}