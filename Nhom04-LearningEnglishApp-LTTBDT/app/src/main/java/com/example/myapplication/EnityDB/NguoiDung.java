package com.example.myapplication.EnityDB;

import java.time.LocalDate;
import java.util.Date;

public class NguoiDung {
    private int idND;
    private String hoTen;
    private LocalDate ngaySinh;
    private boolean gioiTinh;
    private String soDienThoai;
    private boolean idAdmin;
    private TaiKhoan idTaiKhoan;

    public NguoiDung() {
    }

    public NguoiDung(int idND) {
        this.idND = idND;
    }

    public NguoiDung(int idND, String hoTen, LocalDate ngaySinh, boolean gioiTinh, String soDienThoai, boolean idAdmin, TaiKhoan idTaiKhoan) {
        this.idND = idND;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
        this.idAdmin = idAdmin;
        this.idTaiKhoan = idTaiKhoan;
    }

    public int getIdND() {
        return idND;
    }

    public void setIdND(int idND) {
        this.idND = idND;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public boolean isIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(boolean idAdmin) {
        this.idAdmin = idAdmin;
    }

    public TaiKhoan getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(TaiKhoan idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    @Override
    public String toString() {
        return "NguoiDung{" +
                "idND=" + idND +
                ", hoTen='" + hoTen + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", gioiTinh=" + gioiTinh +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", idAdmin=" + idAdmin +
                ", idTaiKhoan=" + idTaiKhoan +
                '}';
    }
}
