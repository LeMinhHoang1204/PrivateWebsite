package com.example.springbootreact.pw.Repository;

import com.example.springbootreact.pw.Entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {
}
