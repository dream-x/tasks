package com.assignment.employee;

import com.assignment.exception.DuplicateResourceException;
import com.assignment.exception.ResourceNotFoundException;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("No employees found!");
        }
        return employees.stream()
                .map(employeeMapper::map)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::map)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find employee " + id));
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepository.existsEmployeeByEmail(employeeDTO.email())){
            throw new DuplicateResourceException("Email already exists!");
        }
        Employee employee = employeeRepository.save(employeeMapper.map(employeeDTO));
        return employeeMapper.map(employee);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeUpdateRequest employeeUpdateRequest) {
              return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setJobTitle(employeeUpdateRequest.jobTitle());
                    employee.setSeniority(employeeUpdateRequest.seniority());
                    employeeRepository.save(employee);
                    return employeeMapper.map(employee);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Could not find employee " + id));
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Could not find employee " + id));
        employeeRepository.deleteById(id);
    }
}
