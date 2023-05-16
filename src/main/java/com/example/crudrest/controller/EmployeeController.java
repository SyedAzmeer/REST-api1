package com.example.crudrest.controller;

import com.example.crudrest.model.Employee;
import com.example.crudrest.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public class EmployeeController {

    private EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService){
        super();
        this.employeeService = employeeService;
    }

    // create employee REST API
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    // build get all employee REST API
    @GetMapping
    public List<Employee> getAllEmployee(Employee employee){
        return employeeService.getAllEmployees();
    }

    // build get all employee by id REST API
    @GetMapping
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
       return new ResponseEntity<Employee>(employeeService.getEmployeeByID(employeeId),HttpStatus.OK);
    }

    // build update employee REST API
    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId, @RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,employeeId),HttpStatus.OK);
    }

    // build delete employee REST API
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId){

        employeeService.deleteEmployee(employeeId);

        return new ResponseEntity<String>("Employee deleted successfully!",HttpStatus.OK);
    }



}
