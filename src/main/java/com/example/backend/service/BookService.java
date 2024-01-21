package com.example.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.common.CustomException;
import com.example.backend.model.Book;
import com.example.backend.repository.BookRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Service
public class BookService {
@Autowired
    private BookRepository bookRepository;

    @Transactional
    public boolean borrowBook(int targetId, int userId) {
        try {
            // 呼叫 repository 更新 Inventory 狀態
            bookRepository.setBookStatus(targetId, "已借出", userId);
            // 新增借閱紀錄
            bookRepository.insertBorrowingRecord(targetId, userId);

            return true;
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("借書失敗，無法更新庫存狀態或新增借閱紀錄", HttpStatus.BAD_REQUEST);
        }
    }
    @Transactional
    public boolean returnBook(int targetId, int userId) {
        try {
            // 呼叫 repository 更新 Inventory 狀態
            bookRepository.setBookStatus(targetId, "可借閱", userId);
            // 更新借閱紀錄中的還書時間
            bookRepository.updateReturnTime(targetId, userId);

            return true;
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("還書失敗，無法更新庫存狀態或更新還書時間", HttpStatus.BAD_REQUEST);
        }
    }
    public List<Book> getAllAvailableBooks() {
        return bookRepository.findAllAvailableBooks();
    }

}
