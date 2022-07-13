package com.example.homework213.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;



public class Employee {
    private String fullName;
    private Integer department;
    private Integer salary;

    public Employee(String fullName, Integer department, Integer salary) {
        this.fullName = StringUtils.capitalize(fullName);
        this.department = department;
        this.salary = salary;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFirstName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return fullName.equals(employee.fullName) && department == employee.department && salary == employee.salary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, department, salary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "ФИО: " + fullName + '\'' +
                ", Отдел: " + department +
                ", Зарплата = " + salary +
                '}';
    }
}





