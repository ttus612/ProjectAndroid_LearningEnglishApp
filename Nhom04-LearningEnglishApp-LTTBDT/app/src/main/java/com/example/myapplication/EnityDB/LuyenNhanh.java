package com.example.myapplication.EnityDB;

import java.sql.Time;
import java.util.Calendar;

public class LuyenNhanh {
    private int idLN;
    private int soLuongCauHoiGioiHan;
    private Calendar thoiGianBatDau;
    private Calendar thoiGianKetThuc;
    private NguoiDung idND;

    public LuyenNhanh() {
    }

    public LuyenNhanh(int idLN) {
        this.idLN = idLN;
    }

    public LuyenNhanh(int idLN, int soLuongCauHoiGioiHan, Calendar thoiGianBatDau, Calendar thoiGianKetThuc, NguoiDung idND) {
        this.idLN = idLN;
        this.soLuongCauHoiGioiHan = soLuongCauHoiGioiHan;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.idND = idND;
    }

    public int getIdLN() {
        return idLN;
    }

    public void setIdLN(int idLN) {
        this.idLN = idLN;
    }

    public int getSoLuongCauHoiGioiHan() {
        return soLuongCauHoiGioiHan;
    }

    public void setSoLuongCauHoiGioiHan(int soLuongCauHoiGioiHan) {
        this.soLuongCauHoiGioiHan = soLuongCauHoiGioiHan;
    }

    public Calendar getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(Calendar thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public Calendar getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(Calendar thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public NguoiDung getIdND() {
        return idND;
    }

    public void setIdND(NguoiDung idND) {
        this.idND = idND;
    }

    @Override
    public String toString() {
        return "LuyenNhanh{" +
                "idLN=" + idLN +
                ", soLuongCauHoiGioiHan=" + soLuongCauHoiGioiHan +
                ", thoiGianBatDau=" + thoiGianBatDau +
                ", thoiGianKetThuc=" + thoiGianKetThuc +
                ", idND=" + idND +
                '}';
    }
}
