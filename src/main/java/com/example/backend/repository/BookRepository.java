package com.example.backend.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.backend.common.CustomException;
import com.example.backend.model.Book;

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

    public List<Book> findAllAvailableBooks() {
        String sql = "SELECT * FROM books WHERE status = 'AVAILABLE'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }
    public boolean updateReturnTime(int targetId, int userId) {
        try {
            // 更新還書時間
            String updateReturnTimeSql = "UPDATE BorrowingRecord SET returntime = NOW() WHERE inventoryid = ? AND userid = ?";
            int rowsAffected = jdbcTemplate.update(updateReturnTimeSql, targetId, userId);
            // 檢查是否有行受到影響
            return rowsAffected > 0;
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CustomException("更新還書時間失敗", HttpStatus.BAD_REQUEST);
        }
    }

}
// 新增借閱紀錄
/*
 * String insertBorrowingRecordSql =
 * "INSERT INTO BorrowingRecord (inventoryid, userid, borrowtime) VALUES (?, ?, NOW())"
 * ;
 * jdbcTemplate.update(insertBorrowingRecordSql, targetId, userId);
 */