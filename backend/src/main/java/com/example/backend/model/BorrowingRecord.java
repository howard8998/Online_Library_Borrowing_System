package com.example.backend.model;

import java.time.LocalDateTime;

public class BorrowingRecord {
    private Long userId;
    private Long inventoryId;
    private LocalDateTime borrowingTime;
    private LocalDateTime returnTime;

    public BorrowingRecord() {
    }

    public BorrowingRecord(Long userId, Long inventoryId, LocalDateTime borrowingTime, LocalDateTime returnTime) {
        this.userId = userId;
        this.inventoryId = inventoryId;
        this.borrowingTime = borrowingTime;
        this.returnTime = returnTime;
    }
}
