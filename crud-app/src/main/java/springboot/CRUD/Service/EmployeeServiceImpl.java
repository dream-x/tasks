package springboot.CRUD.Service;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import springboot.CRUD.DTO.EmployeeIn;
import springboot.CRUD.DTO.EmployeeOut;
import springboot.CRUD.DTO.UpdateEmployee;
import springboot.CRUD.Entity.Employee;
import springboot.CRUD.Mapper.EmployeeMapper;
import springboot.CRUD.Repository.EmployeeRepository;

@AllArgsConstructor
@Component
public class EmployeeServiceImpl implements EmployeeService {
	
	private final EmployeeRepository repository;
	private final EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);
	
	@Override
	public ResponseEntity<EmployeeOut> create(EmployeeIn dtoIn) {
		Employee employee = mapper.map(dtoIn);
        repository.save(employee);
        return ResponseEntity.ok(mapper.map(employee));
	}
	
	@Override
	public ResponseEntity<EmployeeOut> update(UpdateEmployee dtoUpdate) {
		Optional<Employee> employee = repository.findById(dtoUpdate.getId());
		if(employee.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Employee newValue = mapper.map(dtoUpdate);
        repository.save(newValue);
        return ResponseEntity.ok(mapper.map(newValue));
	}
	
	@Override
	public ResponseEntity<Object> getAll(HttpServletResponse servlet) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ResponseEntity<EmployeeOut> get(UUID id) {
		Optional<Employee> data = repository.findById(id);
        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Employee employee = data.get();
        return ResponseEntity.ok(mapper.map(employee));
	}
	
	@Override
	public ResponseEntity<EmployeeOut> delete(UUID id) {
		Optional<Employee> optional = repository.findById(id);
		if(optional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Employee employee = optional.get();
		repository.delete(employee);
		return ResponseEntity.ok(mapper.map(employee));
	}
}
