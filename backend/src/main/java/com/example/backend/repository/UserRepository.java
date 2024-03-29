package com.example.backend.repository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.backend.common.CustomException;

import jakarta.transaction.Transactional;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isUserExists(String phoneNumber) {
        String sql = "SELECT COUNT(*) FROM users WHERE phonenumber = CAST(? AS INT)";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, phoneNumber);
        return count > 0;
    }

    @Transactional
    public void registerUser(String phoneNumber, String hashedPassword, String userName,
            LocalDateTime registrationTime) {
        try {
            String sql = "INSERT INTO Users (PhoneNumber, Password, UserName, RegistrationTime) VALUES (CAST(? AS INT), ?, ?, ?)";
            jdbcTemplate.update(sql, phoneNumber, hashedPassword, userName, registrationTime);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CustomException("註冊失敗", HttpStatus.BAD_REQUEST);
        }
    }

    public String getUserIDByPhoneNumber(String phoneNumber) {
        try {
            String query = "SELECT id FROM Users WHERE PhoneNumber = CAST(? AS INT)";
            return jdbcTemplate.queryForObject(query, String.class, phoneNumber);
        } catch (Exception e) {
            // 處理異常，例如數據庫查詢失敗
            e.printStackTrace();
            throw new CustomException("Failed to get user ID by phone number", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String getPasswordByPhoneNumber(String phoneNumber) {
        try {
            String sql = "SELECT Password FROM Users WHERE PhoneNumber =CAST(? AS INT)";
            return jdbcTemplate.queryForObject(sql, String.class, phoneNumber);
        } catch (EmptyResultDataAccessException e) {
            return null; // 用戶不存在
        }
    }
}
