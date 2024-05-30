package com.example.springbootreact.pw.Repository;

import com.example.springbootreact.pw.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("SELECT e FROM Employee e WHERE e.Email = ?1")
    Optional<Account> findByEmpId_Email(String email);

    @Query("SELECT a FROM Account a WHERE a.employee.Id = ?1")
    Optional<Account> findByEmpId(Integer empid);
}

