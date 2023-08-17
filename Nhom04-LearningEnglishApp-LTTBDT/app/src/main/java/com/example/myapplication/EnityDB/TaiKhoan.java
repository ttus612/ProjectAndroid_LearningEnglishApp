package com.example.myapplication.EnityDB;

public class TaiKhoan {
    private String userName;
    private String passWord;

    public TaiKhoan() {
    }

    public TaiKhoan(String userName) {
        this.userName = userName;
    }

    public TaiKhoan(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
