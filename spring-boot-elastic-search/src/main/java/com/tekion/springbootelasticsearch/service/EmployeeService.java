package com.tekion.springbootelasticsearch.service;

import com.tekion.springbootelasticsearch.dto.EmployeeDTO;
import com.tekion.springbootelasticsearch.entity.Employee;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonValue;
import java.io.StringWriter;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public Employee create( EmployeeDTO employee) ;
    public Optional<Employee> findById(String id) ;

    public List<Employee> findAll() ;

    public Employee update(String id, EmployeeDTO employeeDTO) ;

    public void delete( String id) ;

    public StringWriter jsonDiff() ;

}
