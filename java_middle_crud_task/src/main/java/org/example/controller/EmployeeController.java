package org.example.controller;

import org.example.exception.EmployeeNotFoundException;
import org.example.entity.Employee;
import org.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable Long id) throws EmployeeNotFoundException {
        return employeeService.get(id);
    }

    @GetMapping(value = "/employees", produces = "text/csv")
    public ResponseEntity<Resource> getAllEmployees() {
        return employeeService.getAll();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Long id) throws EmployeeNotFoundException {
        return employeeService.update(employee);
    }

    @DeleteMapping("/employees/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }
}
