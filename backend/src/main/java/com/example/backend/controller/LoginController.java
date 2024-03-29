package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.common.CustomException;
import com.example.backend.service.UserService;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/public/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, String> loginRequest) {
        String phoneNumber = loginRequest.get("phoneNumber");
        String password = loginRequest.get("password");
        
        try {
            // 進行登入驗證
            boolean isAuthenticated = userService.authenticateUser(phoneNumber, password);

            if (isAuthenticated) {
                // 登入成功
                String token = generateToken(phoneNumber);
                String userId = userService.getUserId(phoneNumber);
                Map<String, String> response = new HashMap<>();
                response.put("status", "success");
                response.put("token", token);
                response.put("userId", userId);
                return ResponseEntity.ok(response);
            } else {
                // 登入失敗
                Map<String, String> response = new HashMap<>();
                response.put("status", "error");
                response.put("message", "Authentication failed");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
        }
        catch (CustomException e) {
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

    private String generateToken(String phoneNumber) {
        long expirationMillis = 3600000; // Token 過期時間（1小時）
        Dotenv dotenv = Dotenv.configure().load();
        // 產生一個安全的秘密金鑰
        Key key = Keys.hmacShaKeyFor(dotenv.get("JWT_SECRET").getBytes());

        return Jwts.builder()
                .setSubject(phoneNumber)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(key)
                .compact();
    }
}
