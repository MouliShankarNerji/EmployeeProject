package com.employeeProject.EmployeeProject;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TaxRepository taxRepository;

    @PostMapping("/store")
    public ResponseEntity<String> storeEmployeeDetails(@RequestBody Employee employee) {
       
        if (!isValidEmployee(employee)) {
            return ResponseEntity.badRequest().body("Invalid employee data");
        }
       
        employeeService.storeEmployee(employee);
        return ResponseEntity.ok("Employee details stored successfully");
    }

    @GetMapping("/tax-deduction")
    public ResponseEntity<List<TaxDeduction>> getTaxDeductionForCurrentYear() {
        List<TaxDeduction> taxDeductions = employeeService.calculateTaxDeductionForCurrentYear();

        taxRepository.saveAll(taxDeductions);
        return ResponseEntity.ok(taxDeductions);
    }

    
    private boolean isValidEmployee(Employee employee) {
        if (employee == null) {
            return false;
        }
        
        return true;
    }
}



