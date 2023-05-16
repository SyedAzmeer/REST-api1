package com.example.crudrest.impl;

import com.example.crudrest.exception.ResourceNotFoundException;
import com.example.crudrest.model.Employee;
import com.example.crudrest.repository.EmployeeRepository;
import com.example.crudrest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;



    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        super();
        this.employeeRepository = employeeRepository;
    }

    // create employee REST API
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // build get all employee REST API
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // build get all employee by id REST API
    @Override
    public Employee getEmployeeByID(long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","Id",id));
    }

    // build update employee REST API
    @Override
    public Employee updateEmployee(Employee employee, long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Id", id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    // build delete employee REST API
    @Override
    public void deleteEmployee(long id) {
        employeeRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Employee","Id",id));
        employeeRepository.deleteById(id);
    }
}
