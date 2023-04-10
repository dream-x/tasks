package springboot.CRUD.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeIn {
	private String firstName;
	private String lastName;
}
