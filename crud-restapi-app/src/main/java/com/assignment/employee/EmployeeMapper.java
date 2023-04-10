package com.assignment.employee;

import org.mapstruct.Mapper;

@Mapper
public interface EmployeeMapper {


    EmployeeDTO map(Employee employee);
    Employee map(EmployeeDTO employeeDTO);
}
