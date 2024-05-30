//package com.example.springbootreact.pw.Service;
//
//import com.example.springbootreact.pw.Entity.Account;
//import com.example.springbootreact.pw.Repository.AccountRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.Optional;
//
//@Service
//public class AccountService {
//
//    @Autowired
//    private final AccountRepository accountRepository;
//
//    public AccountService(AccountRepository accountRepository) {
//        this.accountRepository = accountRepository;
//    }
//
//    public Account saveAccount(Account account) {
//        return accountRepository.save(account);
//    }
//
//    public Optional<Account> getAccount(int id) {
//        return accountRepository.findById(id);
//    }
//
//    public void deleteAccount(int id) {
//        accountRepository.deleteById(id);
//    }
//}
