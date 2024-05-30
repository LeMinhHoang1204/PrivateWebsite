package com.example.springbootreact.pw.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String Name;

    @JsonProperty("citizen_id")
    @Column(name = "CITIZEN_ID", nullable = true)
    private String Citizen_Id;

    @JsonProperty("phone_number")
    @Column(name = "PHONE_NUMBER", nullable = true)
    private String PhoneNumber;

    private LocalDate DOB;

    @Column(nullable = true)
    private int Emp_Type;

    @Column(nullable = true)
    private String Email;

    @Column(nullable = true)
    private int Sex;

    private LocalDate Start_Date;

    @ManyToOne
    @JoinColumn(name = "Dept_Id",  referencedColumnName = "Id")
    private Department Department;

    @ManyToOne
    @JoinColumn(name = "Manager_Id", referencedColumnName = "Id", nullable = true)
    private Employee Manager;

    public void setManager_Id(int id) {
        setManager_Id(id);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Employee getManager() {
        return Manager;
    }
}
