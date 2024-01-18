package com.example.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.common.CustomException;
import com.example.backend.service.UserService;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/public/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, String> registrationRequest) {
        String phoneNumber = registrationRequest.get("phoneNumber");
        String password = registrationRequest.get("password");
        String userName = registrationRequest.get("userName");
        
        try {
            // 檢查用戶是否已存在

            if (userService.isUserExists(phoneNumber)) {
                throw new CustomException("User already exists", HttpStatus.BAD_REQUEST);
            }

            // 註冊新使用者
            userService.registerUser(phoneNumber, password, userName);

            // 註冊成功
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", e.getMessage());

            HttpStatus status = e.getStatusCode();
            if (status != null) {
                return ResponseEntity.status(status).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }
    }
}
