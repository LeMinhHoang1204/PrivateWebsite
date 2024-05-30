package com.example.springbootreact.pw.Repository;

import com.example.springbootreact.pw.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT s FROM Employee s WHERE s.Email = ?1")
    Optional<Employee> findEmployeeByEmail(String email);

}
