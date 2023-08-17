package com.example.myapplication.EnityDB;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class DocHieuHTCau implements Parcelable {
    private DocHieu idDH;
    private String dapAnA;
    private String dapAnB;
    private String dapAnC;
    private String dapAnD;
    private String dapAnDung;

    public DocHieuHTCau() {
    }

    public DocHieuHTCau(DocHieu idDH) {
        this.idDH = idDH;
    }

    public DocHieuHTCau(DocHieu idDH, String dapAnA, String dapAnB, String dapAnC, String dapAnD, String dapAnDung) {
        this.idDH = idDH;
        this.dapAnA = dapAnA;
        this.dapAnB = dapAnB;
        this.dapAnC = dapAnC;
        this.dapAnD = dapAnD;
        this.dapAnDung = dapAnDung;
    }

    protected DocHieuHTCau(Parcel in) {
        idDH = in.readParcelable(DocHieu.class.getClassLoader());
        dapAnA = in.readString();
        dapAnB = in.readString();
        dapAnC = in.readString();
        dapAnD = in.readString();
        dapAnDung = in.readString();
    }

    public static final Creator<DocHieuHTCau> CREATOR = new Creator<DocHieuHTCau>() {
        @Override
        public DocHieuHTCau createFromParcel(Parcel in) {
            return new DocHieuHTCau(in);
        }

        @Override
        public DocHieuHTCau[] newArray(int size) {
            return new DocHieuHTCau[size];
        }
    };

    public DocHieu getIdDH() {
        return idDH;
    }

    public void setIdDH(DocHieu idDH) {
        this.idDH = idDH;
    }

    public String getDapAnA() {
        return dapAnA;
    }

    public void setDapAnA(String dapAnA) {
        this.dapAnA = dapAnA;
    }

    public String getDapAnB() {
        return dapAnB;
    }

    public void setDapAnB(String dapAnB) {
        this.dapAnB = dapAnB;
    }

    public String getDapAnC() {
        return dapAnC;
    }

    public void setDapAnC(String dapAnC) {
        this.dapAnC = dapAnC;
    }

    public String getDapAnD() {
        return dapAnD;
    }

    public void setDapAnD(String dapAnD) {
        this.dapAnD = dapAnD;
    }

    public String getDapAnDung() {
        return dapAnDung;
    }

    public void setDapAnDung(String dapAnDung) {
        this.dapAnDung = dapAnDung;
    }

    @Override
    public String toString() {
        return "DocHieuHTCau{" +
                "idDH=" + idDH +
                ", dapAnA='" + dapAnA + '\'' +
                ", dapAnB='" + dapAnB + '\'' +
                ", dapAnC='" + dapAnC + '\'' +
                ", dapAnD='" + dapAnD + '\'' +
                ", dapAnDung='" + dapAnDung + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeParcelable(idDH, flags);
        dest.writeString(dapAnA);
        dest.writeString(dapAnB);
        dest.writeString(dapAnC);
        dest.writeString(dapAnD);
        dest.writeString(dapAnDung);
    }
}
