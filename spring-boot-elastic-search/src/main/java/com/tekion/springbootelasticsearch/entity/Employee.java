package com.tekion.springbootelasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tekion.springbootelasticsearch.constants.KafkaConstants;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Document(indexName = KafkaConstants.INDEX_NAME)
public class Employee {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd")
//    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING, timezone = "Asia/Kolkata")
    private LocalDate doj;
    private BigDecimal salary;
    private Boolean isActive;
    private Boolean type;
    private Long mobileNo;
    private List<Address> addresses;
}