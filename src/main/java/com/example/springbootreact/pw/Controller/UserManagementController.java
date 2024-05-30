package com.example.springbootreact.pw.Controller;

import com.example.springbootreact.pw.dto.ReqRes;
import com.example.springbootreact.pw.Entity.Account;
import com.example.springbootreact.pw.Service.UsersManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserManagementController {

    private UsersManagementService usersManagementService;

    @Autowired
    public void UsersManagementService(UsersManagementService usersManagementService) {
        this.usersManagementService = usersManagementService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> register(@RequestBody ReqRes reg){
        return ResponseEntity.ok(usersManagementService.register(reg));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.refreshToken(req));
    }

    @GetMapping("/admin/get-all-accounts")
    public ResponseEntity<ReqRes> getAllAccounts(){
        return ResponseEntity.ok(usersManagementService.getAllAccounts());
    }

    @GetMapping("/admin/get-account/{userId}")
    public ResponseEntity<ReqRes> getUSerByID(@PathVariable Integer userId){
        return ResponseEntity.ok(usersManagementService.getAccountByEmpId(userId));
    }

    @PutMapping("/admin/updateAccount/{userId}")
    public ResponseEntity<ReqRes> updateAccount(@PathVariable Integer userId, @RequestBody Account reqres){
        return ResponseEntity.ok(usersManagementService.updateAccount(userId, reqres));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<ReqRes> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
        Integer empid = Integer.valueOf(authentication.getName());
        ReqRes response = usersManagementService.getMyInfo(empid);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<ReqRes> deleteUSer(@PathVariable Integer userId){
        return ResponseEntity.ok(usersManagementService.deleteAccount(userId));
    }


}