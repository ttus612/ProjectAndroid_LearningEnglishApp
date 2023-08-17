package com.example.myapplication.Entity;

import java.io.Serializable;

public class TuVung implements Serializable {
    private String tiengAnh;
    private String tiengViet;

    public TuVung(String tiengAnh, String tiengViet) {
        this.tiengAnh = tiengAnh;
        this.tiengViet = tiengViet;
    }

    public String getTiengAnh() {
        return tiengAnh;
    }

    public void setTiengAnh(String tiengAnh) {
        this.tiengAnh = tiengAnh;
    }

    public String getTiengViet() {
        return tiengViet;
    }

    public void setTiengViet(String tiengViet) {
        this.tiengViet = tiengViet;
    }
}
