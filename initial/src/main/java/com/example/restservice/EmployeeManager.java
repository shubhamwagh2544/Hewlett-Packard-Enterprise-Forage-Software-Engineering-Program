package com.example.restservice;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeManager {

    public List<Employee> employeeData() {
        final String role = "Software Engineer";
        Employee robert = new Employee(
                1L,
                "Robert",
                "Downey Jr",
                "robertdowneyjr@hpe.com",
                role
        );
        Employee emily = new Employee(
                2L,
                "Emily",
                "Blunt",
                "emilyblunt@hpe.com",
                role
        );
        Employee cillian = new Employee(
                3L,
                "Cillian",
                "Murphy",
                "cillianmurpy@hpe.com",
                role
        );

        Employees employees = new Employees();

        employees.setEmployeeList(List.of(
                robert,
                emily,
                cillian
        ));

        return employees.getEmployeeList();
    }

}
