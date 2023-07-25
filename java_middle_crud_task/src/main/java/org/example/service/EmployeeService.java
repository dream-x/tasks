package org.example.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.example.entity.Employee;
import org.example.exception.EmployeeNotFoundException;
import org.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public <T> Employee get(Long id) throws EmployeeNotFoundException {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public ResponseEntity<Resource> getAll() {
        String[] csvHeaders = {
                "id", "name"
        };

        List<Employee> employees = employeeRepository.findAll();

        List<List<String>> csvBody = new ArrayList<>();
        for (Employee employee : employees) {
            csvBody.add(Arrays.asList(employee.getId().toString(), employee.getName()));
        }

        ByteArrayInputStream byteArrayOutputStream;
        try (
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                CSVPrinter csvPrinter = new CSVPrinter(
                        new PrintWriter(out),
                        CSVFormat.DEFAULT.withHeader(csvHeaders)
                );
        ) {
            for (List<String> record : csvBody)
                csvPrinter.printRecord(record);
            csvPrinter.flush();
            byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        InputStreamResource fileInputStream = new InputStreamResource(byteArrayOutputStream);
        String csvFileName = "employees.csv";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFileName);
        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");

        return new ResponseEntity<>(
                fileInputStream,
                headers,
                HttpStatus.OK
        );
    }

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee) throws EmployeeNotFoundException {
        Employee e = get(employee.getId());
        e.setName(employee.getName());
        return employeeRepository.save(e);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
