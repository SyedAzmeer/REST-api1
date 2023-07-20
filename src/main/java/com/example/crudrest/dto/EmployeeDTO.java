package com.example.crudrest.dto;

public record EmployeeDTO(
        Long id,
        String firstName,
        String lastName,
        String userName
) {

}
