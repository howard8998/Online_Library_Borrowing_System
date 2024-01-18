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

    // Getter 和 Setter 方法
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public LocalDateTime getBorrowingTime() {
        return borrowingTime;
    }

    public void setBorrowingTime(LocalDateTime borrowingTime) {
        this.borrowingTime = borrowingTime;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }
}
