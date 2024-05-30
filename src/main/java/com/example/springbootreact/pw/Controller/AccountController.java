//package com.example.springbootreact.pw.Controller;
//
//import com.example.springbootreact.pw.Entity.Account;
//import com.example.springbootreact.pw.Service.AccountService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/accounts")
//public class AccountController {
//
//    @Autowired
//    private final AccountService accountService;
//
//    public AccountController(AccountService accountService) {
//        this.accountService = accountService;
//    }
//
//    private final String uploadDir = "uploads/";
//
//    @PostMapping("/add-account")
//    public ResponseEntity<String> createAccount(
//            @RequestParam("imageFile" ) MultipartFile imageFile,
//            @RequestParam("id") int id,
//            @RequestParam("empId") int empId,
//            @RequestParam("password") String password,
//            @RequestParam("status") int status,
//            @RequestParam("role") String role,
//            @RequestParam("bio") String bio) {try {
//            String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
//            Path path = Paths.get(uploadDir + fileName);
//            Files.copy(imageFile.getInputStream(), path);
//
//            Account account = new Account();
//            account.setId(id);
//            account.setEmpId(empId);
//            account.setPassword(password);
//            account.setStatus(status);
//            account.setRole(role);
//            account.setBio(bio);
//            account.setImagePath(fileName);
//
//            accountService.saveAccount(account);
//            return ResponseEntity.ok("Account created successfully");
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating account");
//        }
//    }
//
//    @GetMapping("/get/{id}")
//    public ResponseEntity<Account> getAccount(@PathVariable int id) {
//        Optional<Account> account = accountService.getAccount(id);
//        return account.map(ResponseEntity::ok).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }
//}
