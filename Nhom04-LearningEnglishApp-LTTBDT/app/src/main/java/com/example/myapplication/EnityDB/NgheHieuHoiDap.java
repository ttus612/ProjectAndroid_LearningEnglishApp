package com.example.myapplication.EnityDB;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class NgheHieuHoiDap implements Parcelable {
    private NgheHieu idNH;
    private String dapAnA;
    private String dapAnB;
    private String dapAnC;
    private String dapAnDung;

    public NgheHieuHoiDap() {
    }

    public NgheHieuHoiDap(NgheHieu idNH) {
        this.idNH = idNH;
    }

    public NgheHieuHoiDap(NgheHieu idNH, String dapAnA, String dapAnB, String dapAnC, String dapAnDung) {
        this.idNH = idNH;
        this.dapAnA = dapAnA;
        this.dapAnB = dapAnB;
        this.dapAnC = dapAnC;
        this.dapAnDung = dapAnDung;
    }

    public NgheHieu getIdNH() {
        return idNH;
    }

    public String getDapAnA() {
        return dapAnA;
    }

    public String getDapAnB() {
        return dapAnB;
    }

    public void setIdNH(NgheHieu idNH) {
        this.idNH = idNH;
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

    public void setDapAnDung(String dapAnDung) {
        this.dapAnDung = dapAnDung;
    }

    public String getDapAnC() {
        return dapAnC;
    }

    public String getDapAnDung() {
        return dapAnDung;
    }

    protected NgheHieuHoiDap(Parcel in) {
        idNH = in.readParcelable(NgheHieu.class.getClassLoader());
        dapAnA = in.readString();
        dapAnB = in.readString();
        dapAnC = in.readString();
        dapAnDung = in.readString();
    }

    public static final Creator<NgheHieuHoiDap> CREATOR = new Creator<NgheHieuHoiDap>() {
        @Override
        public NgheHieuHoiDap createFromParcel(Parcel in) {
            return new NgheHieuHoiDap(in);
        }

        @Override
        public NgheHieuHoiDap[] newArray(int size) {
            return new NgheHieuHoiDap[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeParcelable(idNH, flags);
        dest.writeString(dapAnA);
        dest.writeString(dapAnB);
        dest.writeString(dapAnC);
        dest.writeString(dapAnDung);
    }
}
