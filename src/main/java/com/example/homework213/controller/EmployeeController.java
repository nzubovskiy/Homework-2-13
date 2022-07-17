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
    public Employee add(@RequestParam("firstName") String name, @RequestParam("lastName") String surname, @RequestParam("departmentID") int department, @RequestParam double salary) {
        return employeeService.add(name, surname, department, salary);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam  String name, @RequestParam("lastName") String surname) {
        return employeeService.remove(name, surname);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam("firstName") String name, @RequestParam("lastName") String surname) {
        return employeeService.find(name, surname);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }
}

