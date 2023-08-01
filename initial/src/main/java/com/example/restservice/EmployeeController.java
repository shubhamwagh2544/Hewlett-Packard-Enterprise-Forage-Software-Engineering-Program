package com.example.restservice;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeManager employeeManager;

    public EmployeeController(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    @GetMapping(
            path = "employees",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Employee> getAllEmployees() {
        return employeeManager.getEmployeeData();
    }

    @PostMapping(
            path = "employees",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public String addEmployee(@RequestBody Employee employee) {
        return employeeManager.addEmployee(employee);
    }

}
