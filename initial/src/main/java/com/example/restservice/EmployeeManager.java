package com.example.restservice;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeManager {

    private static final List<Employee> list = new ArrayList<>();

    static  {
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

        list.add(robert);
        list.add(emily);
        list.add(cillian);

        employees.setEmployeeList(list);
    }

    public List<Employee> getEmployeeData() {
        return list;
    }

    public String addEmployee(Employee employee) {
        list.add(employee);
        return "success";
    }

}
