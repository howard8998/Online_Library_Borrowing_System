package com.example.backend.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.common.CustomException;
import com.example.backend.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean registerUser(String phoneNumber, String password, String userName) {
        // 加密密碼
        String hashedPassword = passwordEncoder.encode(password);
        // 註冊新使用者
        try {
            userRepository.registerUser(phoneNumber, hashedPassword, userName, LocalDateTime.now());
            return true;
        } catch (Exception e) {
            // 处理注册失败的情况
            return false;
        }
    }

    public boolean isUserExists(String phoneNumber) {
        return userRepository.isUserExists(phoneNumber);
    }

    public boolean authenticateUser(String phoneNumber, String password) {
        String hashedPassword = userRepository.getPasswordByPhoneNumber(phoneNumber);

        if (hashedPassword != null && passwordEncoder.matches(password, hashedPassword)) {
            return true; // 登入驗證成功
        } else {
            throw new CustomException("Authentication failed", HttpStatus.UNAUTHORIZED);
        }
    }
}
