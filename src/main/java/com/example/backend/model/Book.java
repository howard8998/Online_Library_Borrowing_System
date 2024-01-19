package com.example.backend.model;

public class Book {

    private String name;
    private String author;
    private String introduction;

    public Book() {
    }
    
    public Book(String name, String author, String introduction) {
        this.name = name;
        this.author = author;
        this.introduction = introduction;
    }

    // Getter 和 Setter 方法

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
