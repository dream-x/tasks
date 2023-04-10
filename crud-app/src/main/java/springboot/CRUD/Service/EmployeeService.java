package springboot.CRUD.Service;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import springboot.CRUD.DTO.EmployeeIn;
import springboot.CRUD.DTO.EmployeeOut;
import springboot.CRUD.DTO.UpdateEmployee;

public interface EmployeeService {
	
	ResponseEntity<Object> getAll(HttpServletResponse servlet);
	
	ResponseEntity<EmployeeOut> get(UUID id);
	
	ResponseEntity<EmployeeOut> update(UpdateEmployee e);
	
	ResponseEntity<EmployeeOut> delete(UUID id);

	ResponseEntity<EmployeeOut> create(EmployeeIn e);
}
