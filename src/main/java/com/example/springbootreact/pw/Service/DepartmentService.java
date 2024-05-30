package com.example.springbootreact.pw.Service;

import com.example.springbootreact.pw.Entity.Department;
import com.example.springbootreact.pw.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    public void addNewDepartment(Department department) {
        departmentRepository.save(department);
    }

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }

    public void updateDepartment(int id, String name) {
        Department department = departmentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Department is not exists with given id: " + id));
        department.setName(name);
        departmentRepository.save(department);
    }

    public Department getDepartmentById(int id) {
        Department department = departmentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Department is not exists with given id: " + id));
        return department;
    }
}
