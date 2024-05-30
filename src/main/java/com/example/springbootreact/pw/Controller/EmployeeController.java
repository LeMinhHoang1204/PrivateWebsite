package com.example.springbootreact.pw.Controller;

import com.example.springbootreact.pw.Entity.Employee;
import com.example.springbootreact.pw.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    //Build API
    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable int id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/add-employee")
    public void addNewEmployee(@RequestBody Employee employee){
        employeeService.addNewEmployee(employee);
    }
    @DeleteMapping(path = "/delete/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }

//    @PutMapping(path ="/edit-employee/{id}")
//    public void updateEmployee(@PathVariable int id,
//                               @RequestParam (required = false) String phonenumber,
//                               @RequestParam (required = false) LocalDate dob,
//                               @RequestParam (required = false) String email){
//        employeeService.updateEmployee(id, phonenumber, dob, email);
//    }

    @PutMapping(path ="/edit-employee/{id}")
    public void updateEmployee(@PathVariable int id, @RequestBody Map<String, Object> updates){
        employeeService.updateEmployee(id, updates);
    }

}
