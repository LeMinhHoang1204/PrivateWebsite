package com.example.springbootreact.pw.Controller;

import com.example.springbootreact.pw.Entity.Department;
import com.example.springbootreact.pw.Service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.List;

@RestController
@RequestMapping("/departments")
@CrossOrigin(origins = "http://localhost:3000")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService)
    {
        this.departmentService = departmentService;
    }
    @GetMapping
    public List<Department> getDepartments()
    {
        return departmentService.getDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable int id) {
        return departmentService.getDepartmentById(id);
    }


    @PostMapping(path = "/add-department")
    public void addNewDepartment(@RequestBody Department department){
        departmentService.addNewDepartment(department);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
    }

    @PutMapping(path = "/edit-department/{id}")
    public void updateDepartment(@PathVariable int id, @RequestParam String name) {
        departmentService.updateDepartment(id, name);
    }

//    @GetMapping("/departments")
//    public String listStudents(Model model) {
//        model.addAttribute("department", departmentService.getDepartments());
//        return "departments";
//    }
//
//    @GetMapping("/departments/new")
//    public String createDepartmentForm(Model model) {
//
//        // create student object to hold student form data
//        Department department = new Department();
//        model.addAttribute("department", department);
//        return "create_department";
//
//    }
//
//    @PostMapping("/departments")
//    public String saveDepartment(@ModelAttribute("department") Department department) {
//        departmentService.saveDepartment(department);
//        return "redirect:/departments";
//    }
//
//    @GetMapping("/students/edit/{id}")
//    public String editStudentForm(@PathVariable Long id, Model model) {
//        model.addAttribute("student", studentService.getStudentById(id));
//        return "edit_student";
//    }
//
//    @PostMapping("/students/{id}")
//    public String updateStudent(@PathVariable Long id,
//                                @ModelAttribute("student") Student student,
//                                Model model) {
//
//        // get student from database by id
//        Student existingStudent = studentService.getStudentById(id);
//        existingStudent.setId(id);
//        existingStudent.setFirstName(student.getFirstName());
//        existingStudent.setLastName(student.getLastName());
//        existingStudent.setEmail(student.getEmail());
//
//        // save updated student object
//        studentService.updateStudent(existingStudent);
//        return "redirect:/students";
//    }
//
//     handler method to handle delete student request
//
//    @GetMapping("/departments/{id}")
//    public String deleteDepartment(@PathVariable int id) {
//        departmentService.deleteDepartment(id);
//        return "redirect:/departments";
//    }

}
