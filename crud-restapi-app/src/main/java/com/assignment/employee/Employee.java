package com.assignment.employee;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Employee {

    @Id
    @SequenceGenerator(
            name = "employee_id_sequence",
            sequenceName = "employee_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_id_sequence"
    )
    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    private String jobTitle;
    private String seniority;
}
