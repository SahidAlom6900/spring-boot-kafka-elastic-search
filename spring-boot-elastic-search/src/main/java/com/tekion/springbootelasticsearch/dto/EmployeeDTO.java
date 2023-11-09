package com.tekion.springbootelasticsearch.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tekion.springbootelasticsearch.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO implements Serializable {
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
