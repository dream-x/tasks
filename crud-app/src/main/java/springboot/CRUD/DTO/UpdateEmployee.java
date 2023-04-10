package springboot.CRUD.DTO;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployee {
	private UUID id;
	private String firstName;
	private String lastName;
}