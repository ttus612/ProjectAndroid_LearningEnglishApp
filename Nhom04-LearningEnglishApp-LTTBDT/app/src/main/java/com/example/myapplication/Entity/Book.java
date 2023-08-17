package com.example.myapplication.Entity;

import java.io.Serializable;

public class Book implements Serializable {
    private int resoutceId;
    private String title;

    public Book(int resoutceId, String title) {
        this.resoutceId = resoutceId;
        this.title = title;
    }

    public int getResoutceId() {
        return resoutceId;
    }

    public void setResoutceId(int resoutceId) {
        this.resoutceId = resoutceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
