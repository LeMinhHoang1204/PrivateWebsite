package com.example.springbootreact.pw.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;

    @Column(name = "NAME")
    String Name;

    @Column(name = "EMP_TOTAL")
    int Emp_Total;

    public Department(int deptId) {
        Id = deptId;
    }
}
