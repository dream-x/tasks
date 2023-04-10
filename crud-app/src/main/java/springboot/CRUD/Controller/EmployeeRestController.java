package springboot.CRUD.Controller;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import springboot.CRUD.DTO.EmployeeIn;
import springboot.CRUD.DTO.EmployeeOut;
import springboot.CRUD.DTO.UpdateEmployee;
import springboot.CRUD.Service.EmployeeService;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeService service;
	
	@PostMapping("/employees")
	public ResponseEntity<EmployeeOut> create(@RequestBody EmployeeIn dto) {
		return service.create(dto);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeOut> get(@PathVariable UUID id) {
		return service.get(id);
	}
	
	@GetMapping(value = "/employees", produces = "application/csv")
	public @ResponseBody ResponseEntity<Object> getAll(HttpServletResponse response) {
		return service.getAll(response);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<EmployeeOut> update(@RequestBody UpdateEmployee dto) {
		return service.update(dto);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<EmployeeOut> delete(@PathVariable UUID id) {
		return service.delete(id);
	}
}
