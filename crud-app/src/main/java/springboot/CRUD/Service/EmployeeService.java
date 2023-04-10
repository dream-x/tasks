package springboot.CRUD.Service;

import java.util.List;

import springboot.CRUD.Entity.Employee;

public interface EmployeeService {
	
	List<Employee> findAll();
	
	Employee findOne(int id);
	
	Employee updateEmployee(int id, Employee e);
	
	boolean deleteEmployee(int id);

	void addEmployee(Employee e);
}
