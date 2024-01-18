package com.example.backend.repository;

import com.example.backend.model.BorrowingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BorrowingRecordRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<BorrowingRecord> getBorrowingRecordsByUserId(int userId) {
        String sql = "SELECT * FROM BorrowingRecord WHERE userid = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BorrowingRecord.class), userId);
    }

    public List<BorrowingRecord> getAllBorrowingRecords() {
        String sql = "SELECT * FROM BorrowingRecord";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BorrowingRecord.class));
    }

    // 其他相關方法...
}
