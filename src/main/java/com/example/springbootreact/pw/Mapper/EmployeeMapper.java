//package com.example.springbootreact.pw.Mapper;
//
//import com.example.springbootreact.pw.Entity.Employee;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.io.File;
//import java.io.IOException;
//
//public class EmployeeMapper {
//
//    public static void mapEmployee() throws IOException {
//        // Đọc file JSON và chuyển thành đối tượng Employee
//        ObjectMapper objectMapper = new ObjectMapper();
//        Employee employee = objectMapper.readValue(new File("employee.json"), Employee.class);
//
//        // Kiểm tra nếu trường manager là null, gán ID của chính đối tượng cho trường manager_id
//        if ( employee.getManager() == null) {
//            employee.setManager_Id(employee.getId());
//        }
//
//    }
//}
