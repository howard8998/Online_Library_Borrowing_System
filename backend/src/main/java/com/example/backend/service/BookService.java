package com.example.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.common.CustomException;
import com.example.backend.repository.BookRepository;

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
}
