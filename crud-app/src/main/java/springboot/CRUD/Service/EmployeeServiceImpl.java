package springboot.CRUD.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import lombok.AllArgsConstructor;
import springboot.CRUD.CsvUtil.CSVFileCreator;
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
	public ResponseEntity<Object> getAll(HttpServletResponse response) {
		List<Employee> data = repository.findAll();
		String fileName = "employees-csv";
		try {
			File csv = CSVFileCreator.create(data, fileName);
            InputStream in = new FileInputStream(csv);
            response.setContentType("application/csv");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setHeader("Content-Length", String.valueOf(csv.length()));
            FileCopyUtils.copy(in, response.getOutputStream());
            csv.deleteOnExit();
            return ResponseEntity.ok().build();
		} catch (IOException e) {
            return ResponseEntity.internalServerError().body("Failed to create CSV file");
        }
		
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
