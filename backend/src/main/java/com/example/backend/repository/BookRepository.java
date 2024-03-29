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
        String insertBorrowingRecordSql = "INSERT INTO BorrowingRecord (inventoryid, userid, borrowingtime) VALUES (?, ?, NOW())";
        jdbcTemplate.update(insertBorrowingRecordSql, targetId, userId);
    }

    public List<Book> findAllAvailableBooks() {
        try {
            String sql = "SELECT Book.Name AS Name, Book.Author AS Author, Book.Introduction AS Introduction ,Inventory.InventoryID as InventoryID FROM Book JOIN Inventory ON Book.ISBN = Inventory.ISBN WHERE Inventory.Status = '可借閱'";
            List<Book> availableBooks = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
            System.out.println("Available Books:");
            for (Book book : availableBooks) {
                System.out.println("Name: " + book.getName() + ", Author: "
                        + book.getAuthor() + ", Introduction: " + book.getIntroduction());
            }

            return availableBooks;
        } catch (Exception e) {
            // 捕捉並處理任何異常
            e.printStackTrace(); 
            throw new RuntimeException("Error retrieving available books");
        }
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
