package com.example.crudrest.impl;

import com.example.crudrest.dto.EmployeeDTO;
import com.example.crudrest.exception.ResourceNotFoundException;
import com.example.crudrest.mapper.EmployeeDTOMapper;
import com.example.crudrest.model.Employee;
import com.example.crudrest.repository.EmployeeRepository;
import com.example.crudrest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private final EmployeeDTOMapper employeeDTOMapper;



    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeDTOMapper employeeDTOMapper){
        super();
        this.employeeRepository = employeeRepository;
        this.employeeDTOMapper = employeeDTOMapper;
    }

    // create employee REST API
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // build get all employee REST API
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                // if don't have mapper class, can use this way
                //                .map(employee -> new EmployeeDTO(
//                        employee.getId(),
//                        employee.getFirstName(),
//                        employee.getLastName()
//                ))
                .map(employeeDTOMapper)
                .collect(Collectors.toList());
    }

    // build get all employee by id REST API
    @Override
    public EmployeeDTO getEmployeeByID(long id) {
//        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","Id",id));

        return employeeRepository.findById(id)
                .map(employeeDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee","Id",id
                ));
    }

    // build update employee REST API
    @Override
    public Employee updateEmployee(Employee employee, long id) {

        // we need to check whether employee with given id is exist in DB or not
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Id", id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        // save exisiting  employee to DB
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    // build delete employee REST API
    @Override
    public void deleteEmployee(long id) {
        // check whether an employee exist or not in db
        employeeRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Employee","Id",id));
        employeeRepository.deleteById(id);
    }
}
