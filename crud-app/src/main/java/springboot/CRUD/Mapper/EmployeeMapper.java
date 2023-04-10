package springboot.CRUD.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import springboot.CRUD.DTO.EmployeeIn;
import springboot.CRUD.DTO.EmployeeOut;
import springboot.CRUD.DTO.UpdateEmployee;
import springboot.CRUD.Entity.Employee;

@Mapper
public interface EmployeeMapper {
	@Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
	Employee map(EmployeeIn dto);
	Employee map(UpdateEmployee data);
    EmployeeOut map(Employee person);
}