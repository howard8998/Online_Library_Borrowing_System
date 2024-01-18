package com.example.backend.model;

public class Book {

    private Long id;
    private String title;
    private String author;
    private String status;
    private String introduction;

    public Book() {
    }

    public Book(Long id, String title, String author, String status, String introduction) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = status;
        this.introduction = introduction;
    }

    // Getter 和 Setter 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
