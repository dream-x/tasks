package com.assignment.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmployeeDTO(
        Long id,
        @NotBlank String name,
        @NotBlank @Email String email,
        String jobTitle,
        String seniority
) {
}
