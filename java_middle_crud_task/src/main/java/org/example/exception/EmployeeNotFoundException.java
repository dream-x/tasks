package org.example.exception;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(Long id) {
        super("Employee with id: " +id + " is not found");
    }
}
