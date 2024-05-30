package com.example.springbootreact.pw.Service;

import com.example.springbootreact.pw.Entity.Employee;
import com.example.springbootreact.pw.Repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService{
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository)
    {
        this.employeeRepository = employeeRepository;
    }

    public void addNewEmployee(Employee employee) {

        employeeRepository.save(employee);
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    public Employee getEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Employee not found"));
        return employee;
    }

    @Transactional
    public void updateEmployee(int id, Map<String, Object> updates) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        if (updates.containsKey("phone_number")) {
            String phoneNumber = (String) updates.get("phone_number");
            employee.setPhoneNumber(phoneNumber);
        }

        if (updates.containsKey("dob")) {
            LocalDate dob = LocalDate.parse((String) updates.get("dob"));
            employee.setDOB(dob);
        }

        if (updates.containsKey("email")) {
            String email = (String) updates.get("email");
            if (!email.isEmpty() && !employee.getEmail().equals(email)) {
                Optional<Employee> existingEmployee = employeeRepository.findEmployeeByEmail(email);
                if (existingEmployee.isPresent()) {
                    throw new IllegalArgumentException("Employee with email " + email + " already exists");
                }
                employee.setEmail(email);
            }
        }

        employeeRepository.save(employee);
    }

}
