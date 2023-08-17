package com.example.myapplication.EnityDB;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class NgheHieuMoTaTranh implements Parcelable {
    private NgheHieu idNH;
    private String deHinhAnh;
    private String dapAnA;
    private String dapAnB;
    private String dapAnC;
    private String dapAnD;
    private String dapAnDung;

    public NgheHieuMoTaTranh() {
    }

    public NgheHieuMoTaTranh(NgheHieu idNH) {
        this.idNH = idNH;
    }

    public NgheHieuMoTaTranh(NgheHieu idNH, String deHinhAnh, String dapAnA, String dapAnB, String dapAnC, String dapAnD, String dapAnDung) {
        this.idNH = idNH;
        this.deHinhAnh = deHinhAnh;
        this.dapAnA = dapAnA;
        this.dapAnB = dapAnB;
        this.dapAnC = dapAnC;
        this.dapAnD = dapAnD;
        this.dapAnDung = dapAnDung;
    }

    public void setIdNH(NgheHieu idNH) {
        this.idNH = idNH;
    }

    public void setDeHinhAnh(String deHinhAnh) {
        this.deHinhAnh = deHinhAnh;
    }

    public void setDapAnA(String dapAnA) {
        this.dapAnA = dapAnA;
    }

    public void setDapAnB(String dapAnB) {
        this.dapAnB = dapAnB;
    }

    public void setDapAnC(String dapAnC) {
        this.dapAnC = dapAnC;
    }

    public void setDapAnD(String dapAnD) {
        this.dapAnD = dapAnD;
    }

    public void setDapAnDung(String dapAnDung) {
        this.dapAnDung = dapAnDung;
    }

    public NgheHieu getIdNH() {
        return idNH;
    }

    public String getDeHinhAnh() {
        return deHinhAnh;
    }

    public String getDapAnA() {
        return dapAnA;
    }

    public String getDapAnB() {
        return dapAnB;
    }

    public String getDapAnC() {
        return dapAnC;
    }

    public String getDapAnD() {
        return dapAnD;
    }

    public String getDapAnDung() {
        return dapAnDung;
    }

    protected NgheHieuMoTaTranh(Parcel in) {
        idNH = in.readParcelable(NgheHieu.class.getClassLoader());
        deHinhAnh = in.readString();
        dapAnA = in.readString();
        dapAnB = in.readString();
        dapAnC = in.readString();
        dapAnD = in.readString();
        dapAnDung = in.readString();
    }

    public static final Creator<NgheHieuMoTaTranh> CREATOR = new Creator<NgheHieuMoTaTranh>() {
        @Override
        public NgheHieuMoTaTranh createFromParcel(Parcel in) {
            return new NgheHieuMoTaTranh(in);
        }

        @Override
        public NgheHieuMoTaTranh[] newArray(int size) {
            return new NgheHieuMoTaTranh[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeParcelable(idNH, flags);
        dest.writeString(deHinhAnh);
        dest.writeString(dapAnA);
        dest.writeString(dapAnB);
        dest.writeString(dapAnC);
        dest.writeString(dapAnD);
        dest.writeString(dapAnDung);
    }
}
