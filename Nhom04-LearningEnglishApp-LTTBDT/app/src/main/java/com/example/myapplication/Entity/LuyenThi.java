package com.example.myapplication.Entity;

import java.io.Serializable;

public class LuyenThi implements Serializable {
    private String title;
    private int thoiGian;
    private int soCauHoi;

    public LuyenThi(String title, int thoiGian, int soCauHoi) {
        this.title = title;
        this.thoiGian = thoiGian;
        this.soCauHoi = soCauHoi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(int thoiGian) {
        this.thoiGian = thoiGian;
    }

    public int getSoCauHoi() {
        return soCauHoi;
    }

    public void setSoCauHoi(int soCauHoi) {
        this.soCauHoi = soCauHoi;
    }
}
