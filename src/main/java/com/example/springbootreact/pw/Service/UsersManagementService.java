package com.example.springbootreact.pw.Service;

import com.example.springbootreact.pw.Entity.Employee;
import com.example.springbootreact.pw.Repository.EmployeeRepository;
import com.example.springbootreact.pw.dto.ReqRes;
import com.example.springbootreact.pw.Entity.Account;
import com.example.springbootreact.pw.Repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UsersManagementService {

    //@Autowired
    private final AccountRepository accountRepository;
    //@Autowired
    private final JWTUtils jwtUtils;
    //@Autowired
    private final AuthenticationManager authenticationManager;
    //@Autowired
    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public UsersManagementService(AccountRepository accountRepository, JWTUtils jwtUtils,
                                  AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, EmployeeRepository employeeRepository) {
        this.accountRepository = accountRepository;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.employeeRepository = employeeRepository;
    }


    public ReqRes register(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();
        try {
            Account ourUser = new Account();
            ourUser.setStatus(registrationRequest.getStatus());
            ourUser.setImagePath(registrationRequest.getImagepath());
            ourUser.setRole(registrationRequest.getRole());
            ourUser.setBio(registrationRequest.getBio());
            if (registrationRequest.getPassword() == null) {
                throw new IllegalArgumentException("Password cannot be null");
            }
            else {
                ourUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            }
            //ourUser.setPassword(registrationRequest.getPassword());
            Employee employee = employeeRepository.findById(registrationRequest.getEmpid()).get();
            ourUser.setEmployee(employee);
            ourUser.setEmpId(registrationRequest.getEmpid());
            Account AccountResult = accountRepository.save(ourUser);
             if (AccountResult.getId()>0) {
                resp.setAccount((AccountResult));
                resp.setMessage("User Saved Successfully");
                resp.setStatusCode(200);
            }

        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }


    public ReqRes login(ReqRes loginRequest){
        ReqRes response = new ReqRes();
        try {
            Optional<Account> userOpt = accountRepository.findByEmpId(loginRequest.getEmpid());
            if (!userOpt.isPresent()) {
                response.setStatusCode(404);
                response.setMessage("Employee not found");
                return response;
            }
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmpid(),
                            loginRequest.getPassword()));

            Account user = userOpt.orElseThrow(() -> new RuntimeException("User not found"));

            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setEmpid(loginRequest.getEmpid());
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRole(user.getRole());
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Successfully Logged In");

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenReqiest){
        ReqRes response = new ReqRes();
        try{
            Integer empid = jwtUtils.extractUsername(refreshTokenReqiest.getRefreshToken());
            Account users = accountRepository.findByEmpId(empid).orElseThrow();
            if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
                var jwt = jwtUtils.generateToken(users);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenReqiest.getToken());
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully Refreshed Token");
            }
            response.setStatusCode(200);
            return response;

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }


    public ReqRes getAllAccounts() {
        ReqRes reqRes = new ReqRes();

        try {
            List<Account> result = accountRepository.findAll();
            if (!result.isEmpty()) {
                reqRes.setAccountList(result);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("No users found");
            }
            return reqRes;
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
            return reqRes;
        }
    }


    public ReqRes getAccountByEmpId(Integer userId) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<Account> userOptional = accountRepository.findByEmpId(userId);
            if (userOptional.isPresent()) {
                reqRes.setAccount(userOptional.get());
                reqRes.setStatusCode(200);
                reqRes.setMessage("Users with id '" + userId + "' found successfully");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
        }
        return reqRes;
    }


    public ReqRes deleteAccount(Integer userId) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<Account> userOptional = accountRepository.findByEmpId(userId);
            if (userOptional.isPresent()) {
                accountRepository.deleteById(userOptional.get().getId());
                reqRes.setStatusCode(200);
                reqRes.setMessage("User deleted successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for deletion");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while deleting user: " + e.getMessage());
        }
        return reqRes;
    }

    public ReqRes updateAccount(Integer userId, Account updatedAccount) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<Account> userOptional = accountRepository.findByEmpId(userId);
            if (userOptional.isPresent()) {
                Account existingUser = userOptional.get();
                existingUser.setStatus(updatedAccount.getStatus());
                existingUser.setImagePath(updatedAccount.getImagePath());
                existingUser.setBio(updatedAccount.getBio());
                existingUser.setRole(updatedAccount.getRole());

                // Check if password is present in the request
                if (updatedAccount.getPassword() != null && !updatedAccount.getPassword().isEmpty()) {
                    // Encode the password and update it
                    existingUser.setPassword(passwordEncoder.encode(updatedAccount.getPassword()));
                }

                Account savedUser = accountRepository.save(existingUser);
                reqRes.setAccount(savedUser);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User updated successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while updating user: " + e.getMessage());
        }
        return reqRes;
    }


    public ReqRes getMyInfo(Integer userId){
        ReqRes reqRes = new ReqRes();
        try {
            Optional<Account> userOptional = accountRepository.findByEmpId(userId);
            if (userOptional.isPresent()) {
                reqRes.setAccount(userOptional.get());
                reqRes.setStatusCode(200);
                reqRes.setMessage("successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }

        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while getting user info: " + e.getMessage());
        }
        return reqRes;

    }
}