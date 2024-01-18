package com.example.backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.common.CustomException;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 創建新使用者
    @Transactional
    public void createUser(String phoneNumber, String password, String userName) {
        try {
            // 加密密碼
            String hashedPassword = passwordEncoder.encode(password);

            String sql = "INSERT INTO Users (PhoneNumber, Password, UserName, RegistrationTime) VALUES (?, ?, ?, NOW())";
            jdbcTemplate.update(sql, phoneNumber, hashedPassword, userName);
        } catch (DataAccessException e) {
            e.printStackTrace();
            // 註冊失敗，可能是重複的手機號碼或其他原因
            throw new CustomException("註冊失敗", HttpStatus.BAD_REQUEST);
        }
    }

    // 使用手機號碼和密碼進行登入驗證
    public boolean authenticateUser(String phoneNumber, String password) {
        try {
            String sql = "SELECT Password FROM Users WHERE PhoneNumber = ?";
            String hashedPassword = jdbcTemplate.queryForObject(sql, String.class, phoneNumber);

            // 使用PasswordEncoder進行驗證
            return passwordEncoder.matches(password, hashedPassword);
        } catch (EmptyResultDataAccessException e) {
            // 處理查用戶不存在
            throw new CustomException("User not found", HttpStatus.UNAUTHORIZED);
        } catch (DataAccessException e) {
            // 處理其他異常
            throw new CustomException("Authentication failed due to database error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

