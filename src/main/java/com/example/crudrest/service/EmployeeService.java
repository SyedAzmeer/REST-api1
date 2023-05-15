package com.example.crudrest.service;

import com.example.crudrest.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeByID(long id);
    Employee updateEmployee(Employee employee,long id);
    void deleteEmployee(long id);
}
