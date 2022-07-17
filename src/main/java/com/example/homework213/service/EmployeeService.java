package com.example.homework213.service;


import com.example.homework213.exceptions.EmployeeAlreadyAddedException;
import com.example.homework213.exceptions.EmployeeNotFoundException;
import com.example.homework213.exceptions.EmployeeStorageIsFullException;
import com.example.homework213.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeService {

    private static final int LIMIT = 10;

    private final List<Employee> employees = new ArrayList<>();

    private final ValidatorService validatorService;


    public EmployeeService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }
    public Employee add(String name, String surname, int department, double salary) {
        Employee employee = new Employee(validatorService.validateName(name), validatorService.validateSurname(surname), department, salary);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }


    public Employee remove(String name, String surname) {
        Employee employee = employees.stream()
                .filter(emp -> emp.getName().equals(name) && emp.getSurname().equals(surname))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
        employees.remove(employee);
        return employee;
    }

    public Employee find(String name, String surname) {
        Employee employee = employees.stream()
                .filter(emp -> emp.getName().equals(name) && emp.getSurname().equals(surname))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
        return employee;
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }
}





