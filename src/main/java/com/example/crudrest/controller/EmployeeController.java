package com.example.crudrest.controller;

import com.example.crudrest.dto.EmployeeDTO;
import com.example.crudrest.mapper.EmployeeDTOMapper;
import com.example.crudrest.model.Employee;
import com.example.crudrest.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService, EmployeeDTOMapper employeeDTOMapper){
        super();
        this.employeeService = employeeService;
    }

    // create employee REST API
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    // build get all employee REST API
    //http://localhost:8080/api/employees
    @GetMapping
    public List<EmployeeDTO> getAllEmployee(Employee employee){
        return employeeService.getAllEmployees();
    }

    // build get all employee by id REST API
    //http://localhost:8080/api/employees/1
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") long employeeId){
       return new ResponseEntity<EmployeeDTO>(employeeService.getEmployeeByID(employeeId),HttpStatus.OK);
    }

    // build update employee REST API
    //http://localhost:8080/api/employees/1
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId, @RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,employeeId),HttpStatus.OK);
    }

    // build delete employee REST API
    //http://localhost:8080/api/employees/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId){

        employeeService.deleteEmployee(employeeId);

        return new ResponseEntity<String>("Employee deleted successfully!",HttpStatus.OK);
    }



}
