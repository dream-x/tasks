package springboot.CRUD.rest;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.CRUD.DTO.EmployeeIn;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	@PostMapping("/employees")
	public void create(@RequestBody EmployeeIn dto) {
		System.out.println("Create " + dto);
	}
	
	@GetMapping("/employees/{id}")
	public void get(@PathVariable UUID id) {
		System.out.println("Get " + id);
	}
	
	@GetMapping("/employees")
	public void getAll() {
		System.out.println("Get all employees");
	}
	
	@PutMapping("/employees/{id}")
	public void update(@RequestBody EmployeeIn dto) {
		System.out.println("Updated dto to " + dto);
	}
	
	@DeleteMapping("/employees/{id}")
	public void delete(@PathVariable UUID id) {
		System.out.println("Deleted object with id " + id);
	}
}
