package com.employeeProject.EmployeeProject;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void storeEmployee(Employee employee) {
        
        employeeRepository.save(employee);
    }

    public List<TaxDeduction> calculateTaxDeductionForCurrentYear() {
        List<TaxDeduction> taxDeductions = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);

        List<Employee> employees = employeeRepository.findAll();

        for (Employee employee : employees) {

            double totalSalary = calculateTotalSalaryForYear(employee, currentYear, currentMonth);
            double taxAmount = calculateTaxAmount(totalSalary);
            double cessAmount = calculateCessAmount(totalSalary);

            TaxDeduction taxDeduction = new TaxDeduction();
            taxDeduction.setEmployeeId(employee.getEmployeeId());
            taxDeduction.setFirstName(employee.getFirstName());
            taxDeduction.setLastName(employee.getLastName());
            taxDeduction.setYearlySalary(totalSalary);
            taxDeduction.setTaxAmount(taxAmount);
            taxDeduction.setCessAmount(cessAmount);

            taxDeductions.add(taxDeduction);
        }

        return taxDeductions;
    }

    private double calculateTotalSalaryForYear(Employee employee, int currentYear, int currentMonth) {
        Calendar dojCalendar = Calendar.getInstance();
        dojCalendar.setTime(employee.getDoj());
        int dojYear = dojCalendar.get(Calendar.YEAR);
        int dojMonth = dojCalendar.get(Calendar.MONTH);

        double monthlySalary = employee.getSalary();
        double totalSalary = 0;

        if (currentYear > dojYear || (currentYear == dojYear && currentMonth >= dojMonth)) {
            int remainingMonths = (currentYear - dojYear) * 12 + currentMonth - dojMonth + 1;
            totalSalary = remainingMonths * monthlySalary;
        }

        return totalSalary;
    }

    private double calculateTaxAmount(double totalSalary) {
        double taxAmount = 0;

        if (totalSalary > 1000000) {
            taxAmount = (totalSalary - 1000000) * 0.2;
            totalSalary = 1000000;
        }
        if (totalSalary > 500000) {
            taxAmount += (totalSalary - 500000) * 0.1;
            totalSalary = 500000;
        }
        if (totalSalary > 250000) {
            taxAmount += (totalSalary - 250000) * 0.05;
        }

        return taxAmount;
    }

    private double calculateCessAmount(double totalSalary) {
        if (totalSalary > 2500000) {
            return (totalSalary - 2500000) * 0.02;
        }
        return 0;
    }
}
