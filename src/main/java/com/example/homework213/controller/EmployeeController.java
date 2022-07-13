package com.example.homework213.controller;

import com.example.homework213.model.Employee;
import com.example.homework213.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam String fullName, @RequestParam Integer department, @RequestParam Integer salary) {
        return employeeService.add(fullName, department, salary);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam String fullName, @RequestParam Integer department, Integer salary) {
        return employeeService.remove(fullName, department, salary);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam String fullName, @RequestParam Integer department, @RequestParam Integer salary) {
        return employeeService.find(fullName, department, salary);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }
}

