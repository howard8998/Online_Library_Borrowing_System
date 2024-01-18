package com.example.backend.model;

public class Book {

    private Long id;
    private String title;
    private String author;
    private String status;
    private String introduction;

    public Book() {
    }

    public Book(Long id, String title, String author, String status, String itroduction) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = status;
        this.introduction = itroduction;
    }

}
