package com.assignment.employee;

import com.assignment.util.CsvWriter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final CsvWriter csvWriter;

    @GetMapping(produces = "text/csv")
    public ResponseEntity<Resource> all() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        ByteArrayResource csvData = csvWriter.writeCsvData(employees);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees.csv");
        return ResponseEntity.ok().headers(headers).body(csvData);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDTO> one(@PathVariable("id") Long id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employeeDTO);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> create(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO newEmployee = employeeService.addEmployee(employeeDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newEmployee.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<EmployeeDTO> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody EmployeeUpdateRequest employeeUpdateRequest) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeUpdateRequest);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok().build();
    }
}
