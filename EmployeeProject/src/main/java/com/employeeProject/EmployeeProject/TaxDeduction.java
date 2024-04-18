package com.employeeProject.EmployeeProject;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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

public class TaxDeduction {
	
	@Id
	
		private String employeeId;
	    private String firstName;
	    private String lastName;
	    private double yearlySalary;
	    private double taxAmount;
	    private double cessAmount;
}
