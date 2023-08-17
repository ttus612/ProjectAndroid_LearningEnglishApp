package com.example.myapplication.Entity;

import java.util.List;

public class Category {
    private String nameCategory;
    private List<Book> bookList;

    public Category(String nameCategory, List<Book> bookList) {
        this.nameCategory = nameCategory;
        this.bookList = bookList;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
