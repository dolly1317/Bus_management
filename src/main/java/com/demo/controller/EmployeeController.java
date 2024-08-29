package com.demo.controller;

import com.demo.entity.Country;
import com.demo.entity.Employee;
import com.demo.repository.CountryRepository;
import com.demo.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")

public class EmployeeController {

    private CountryRepository countryRepository;
    private EmployeeRepository employeeRepository;

    public EmployeeController(CountryRepository countryRepository, EmployeeRepository employeeRepository) {
        this.countryRepository = countryRepository;
        this.employeeRepository = employeeRepository;
    }


    @PostMapping
    public ResponseEntity<Employee> addEmployee(
            @RequestParam long countryId,
            @RequestBody Employee employee
    ){
        Country country = countryRepository.findById(countryId).get();
        employee.setCountry(country);
        Employee savedEmp=employeeRepository.save(employee);
        return ResponseEntity.ok().body(savedEmp);
    }

}
