package org.example.service;

import org.example.entity.Employee;
import org.example.exception.EmployeeNotFoundException;
import org.example.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static junit.framework.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetEmployee() throws EmployeeNotFoundException {
        Employee employee = new Employee(1L, "John");

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        Employee result = employeeService.get(1L);

        assertEquals(employee.getId(), result.getId());
    }

    @Test
    public void shouldCreateEmployee() {
        Employee employee = new Employee(1L, "John");

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.create(employee);

        assertEquals(employee.getId(), result.getId());
    }

    @Test
    public void shouldUpdateEmployee() throws EmployeeNotFoundException {
        Employee employee = new Employee(1L, "John");
        Employee savedEmployee = new Employee(1L, "Kate");

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(savedEmployee);

        Employee result = employeeService.update(savedEmployee);

        assertEquals(savedEmployee.getId(), result.getId());
        assertEquals(savedEmployee.getName(), result.getName());
    }

    @Test
    public void shouldDeleteEmployee() {
        employeeService.delete(1L);

        verify(employeeRepository).deleteById(1L);
    }
}
