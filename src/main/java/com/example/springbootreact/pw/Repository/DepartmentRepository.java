package com.example.springbootreact.pw.Repository;

import com.example.springbootreact.pw.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
