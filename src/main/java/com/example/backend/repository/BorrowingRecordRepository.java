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
        String sql = "SELECT BorrowingRecord.BorrowingTime,Inventory.InventoryID, Book.Name, Book.Author, Book.Introduction " +
        "FROM BorrowingRecord " +
        "JOIN Inventory ON BorrowingRecord.InventoryID = Inventory.InventoryID " +
        "JOIN Book ON Inventory.ISBN = Book.ISBN " +
        "WHERE BorrowingRecord.ReturnTime IS NULL AND BorrowingRecord.UserID = ?";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BorrowingRecord.class), userId);
    }

    public List<BorrowingRecord> getUnreturnBorrowingRecords() {
        String sql = "SELECT BorrowingRecord.BorrowingTime,Inventory.InventoryID,Book.Name, Book.Author, Book.Introduction\r\n" + //
                "FROM BorrowingRecord\r\n" + //
                "JOIN Inventory ON BorrowingRecord.InventoryID = Inventory.InventoryID\r\n" + //
                "JOIN Book ON Inventory.ISBN = Book.ISBN\r\n" + //
                "WHERE BorrowingRecord.ReturnTime IS NULL;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BorrowingRecord.class));
    }

    // 其他相關方法...
}
