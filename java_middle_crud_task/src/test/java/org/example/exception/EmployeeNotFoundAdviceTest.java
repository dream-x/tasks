package org.example.exception;

import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

public class EmployeeNotFoundAdviceTest {

    @Test
    public void shouldTestEmployeeNotFoundExceptionHandler(){
        EmployeeNotFoundAdvice employeeNotFoundAdvice = new EmployeeNotFoundAdvice();

        String result = employeeNotFoundAdvice.employeeNotFoundExceptionHandler(new EmployeeNotFoundException(1L));

        assertEquals(result, "Employee with id: 1 is not found");
    }
}
