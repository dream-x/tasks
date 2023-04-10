package springboot.CRUD.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springboot.CRUD.Entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {}