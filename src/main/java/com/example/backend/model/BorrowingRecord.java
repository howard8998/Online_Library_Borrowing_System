package com.example.backend.model;

import java.time.LocalDateTime;

public class BorrowingRecord {
    private Long userId;
    private Long inventoryId;
    private LocalDateTime borrowingTime;
    private LocalDateTime returnTime;
    private String name;
    private String author;
    private String introduction;

    public BorrowingRecord() {
    }

    public BorrowingRecord(Long userId, Long inventoryId, LocalDateTime borrowingTime, LocalDateTime returnTime) {
        this.userId = userId;
        this.inventoryId = inventoryId;
        this.borrowingTime = borrowingTime;
        this.returnTime = returnTime;
    }

    public BorrowingRecord( LocalDateTime borrowingTime, String name, String author, String introduction) {
        this.borrowingTime = borrowingTime;
        this.name = name;
        this.author = author;
        this.introduction = introduction;
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

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
