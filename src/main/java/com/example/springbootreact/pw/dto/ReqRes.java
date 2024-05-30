package com.example.springbootreact.pw.dto;

import com.example.springbootreact.pw.Entity.Account;
import com.example.springbootreact.pw.Entity.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRes {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String password;
    private Integer status;
    private String role;
    private String imagepath;
    private String bio;
    private Integer empid;
    private String empemail;

//    @OneToOne
//    @JoinColumn(name = "empId",  referencedColumnName = "Id")
//    private Employee employee;

    private Account account;
    private List<Account> accountList;

//    public Integer getEmpId() {
//        return employee.getId();
//    }

//    public Integer getEmpId() {
//        return employee != null ? employee.getId() : null;
//    }



}
