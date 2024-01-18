package com.example.backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Boolean setBookStatus(int targetId, String status, int userId) {
        // 更新 Inventory 狀態
        String updateInventorySql = "UPDATE Inventory SET status = ? WHERE inventoryid = ?";
        int rowsAffected = jdbcTemplate.update(updateInventorySql, status, targetId);
        // 檢查是否有行受到影響
        return rowsAffected > 0;
    }

    public void insertBorrowingRecord(int targetId, int userId) {
        // 新增借閱紀錄
        String insertBorrowingRecordSql = "INSERT INTO BorrowingRecord (inventoryid, userid, borrowtime) VALUES (?, ?, NOW())";
        jdbcTemplate.update(insertBorrowingRecordSql, targetId, userId);
    }
}
// 新增借閱紀錄
/*
 * String insertBorrowingRecordSql =
 * "INSERT INTO BorrowingRecord (inventoryid, userid, borrowtime) VALUES (?, ?, NOW())"
 * ;
 * jdbcTemplate.update(insertBorrowingRecordSql, targetId, userId);
 */