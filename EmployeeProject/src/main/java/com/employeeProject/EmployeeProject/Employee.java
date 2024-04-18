package com.employeeProject.EmployeeProject;

import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
	@Id
	private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumbers;
    private Date doj;
    private double salary;
}
