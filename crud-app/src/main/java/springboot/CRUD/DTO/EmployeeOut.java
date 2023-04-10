package springboot.CRUD.DTO;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeOut {
	private UUID id;
	private String firstName;
	private String lastName;
}
