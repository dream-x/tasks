package com.assignment.util;

import com.assignment.employee.EmployeeDTO;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsvWriter {

    public ByteArrayResource writeCsvData(List<EmployeeDTO> employeeDTOs) {
        StringBuilder csvData = new StringBuilder();
        csvData.append("id, name, email, jobTitle, seniority\n");

        for (EmployeeDTO employeeDTO : employeeDTOs) {
            csvData.append(employeeDTO.id()).append(",")
                    .append(employeeDTO.name()).append(",")
                    .append(employeeDTO.email()).append(",")
                    .append(employeeDTO.jobTitle()).append(",")
                    .append(employeeDTO.seniority()).append("\n");
        }
        return  new ByteArrayResource(csvData.toString().getBytes());
    }
}
