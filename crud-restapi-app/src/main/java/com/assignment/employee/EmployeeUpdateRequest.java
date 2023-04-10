package com.assignment.employee;

import jakarta.validation.constraints.NotBlank;

public record EmployeeUpdateRequest(
        @NotBlank String jobTitle,
        @NotBlank String seniority
) {
}
