package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.common.CustomException;
import com.example.backend.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, String> loginRequest) {
        String phoneNumber = loginRequest.get("phoneNumber");
        String password = loginRequest.get("password");

        try {
            // 進行登入驗證
            boolean isAuthenticated = userService.authenticateUser(phoneNumber, password);

            if (isAuthenticated) {
                // 登入成功
                String token = generateToken(phoneNumber);
                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                return ResponseEntity.ok(response);
            } else {
                // 登入失敗
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } catch (CustomException e) {
            // 處理自定義異常
            return ResponseEntity.status(e.getStatusCode()).body(null);
        }
    }

    private String generateToken(String phoneNumber) {
        long expirationMillis = 3600000; // Token 過期時間（1小時，你可以根據需求調整）

        // 產生一個安全的秘密金鑰
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        return Jwts.builder()
                .setSubject(phoneNumber)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(key)
                .compact();
    }
}
