package com.example.backend.controller;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.common.CustomException;
import com.example.backend.model.Book;
import com.example.backend.model.BorrowingRecord;
import com.example.backend.service.BookService;
import com.example.backend.service.BorrowingRecordService;

@RestController
@RequestMapping("/api")
public class BorrowReturnController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @GetMapping("/available-books")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        try {
            List<Book> availableBooks = bookService.getAllAvailableBooks();
            return ResponseEntity.ok(availableBooks);
        } catch (CustomException e) {
            e.printStackTrace();
            return ResponseEntity.status((HttpStatus) Objects.requireNonNull(e.getStatusCode())).body(Collections.emptyList());
        }
    }

    @GetMapping("/user-borrow-history")
    public ResponseEntity<List<BorrowingRecord>> getUserBorrowHistory(@RequestParam int userId) {
        try {
            List<BorrowingRecord> userBorrowHistory = borrowingRecordService.getBorrowingRecordsByUserId(userId);
            return ResponseEntity.ok(userBorrowHistory);
        } catch (CustomException e) {
            return ResponseEntity.status((HttpStatus) Objects.requireNonNull(e.getStatusCode())).body(Collections.emptyList());
        }
    }

    @GetMapping("/unreturn-borrow-history")
    public ResponseEntity<List<BorrowingRecord>> getAllBorrowHistory() {
        try {
            List<BorrowingRecord> allBorrowHistory = borrowingRecordService.getUnreturnBorrowingRecords();
            return ResponseEntity.ok(allBorrowHistory);
        } catch (CustomException e) {
            return ResponseEntity.status((HttpStatus) Objects.requireNonNull(e.getStatusCode())).body(Collections.emptyList());
        }
    }

    @PutMapping("/borrow")
    public ResponseEntity<String> borrowBook(@RequestParam int bookId, @RequestParam int userId) {
        boolean success = bookService.borrowBook(bookId, userId);
        if (success) {
            return ResponseEntity.ok("Book borrowed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book borrowing failed");
        }
    }

    @DeleteMapping("/return")
    public ResponseEntity<String> returnBook(@RequestParam int bookId, @RequestParam int userId) {
        boolean success = bookService.returnBook(bookId, userId);
        if (success) {
            return ResponseEntity.ok("Book returned successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book returning failed");
        }
    }

}
