package com.example.backend.service;

import com.example.backend.model.BorrowingRecord;
import com.example.backend.repository.BorrowingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowingRecordService {

    @Autowired
    private  BorrowingRecordRepository borrowingRecordRepository;

    public  List<BorrowingRecord> getBorrowingRecordsByUserId(int userId) {
        return borrowingRecordRepository.getBorrowingRecordsByUserId(userId);
    }

    public List<BorrowingRecord> getUnreturnBorrowingRecords() {
        return borrowingRecordRepository.getUnreturnBorrowingRecords();
    }
}
