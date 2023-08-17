package com.example.myapplication.EnityDB;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class DocHieuHTDoanVan implements Parcelable {
    private DocHieu idDH;
    private int idCau;
    private String dapAnA;
    private String dapAnB;
    private String dapAnC;
    private String dapAnD;
    private String dapAnDung;

    public DocHieuHTDoanVan() {
    }

    public DocHieuHTDoanVan(DocHieu idDH) {
        this.idDH = idDH;
    }

    public DocHieuHTDoanVan(DocHieu idDH, int idCau, String dapAnA, String dapAnB, String dapAnC, String dapAnD, String dapAnDung) {
        this.idDH = idDH;
        this.idCau = idCau;
        this.dapAnA = dapAnA;
        this.dapAnB = dapAnB;
        this.dapAnC = dapAnC;
        this.dapAnD = dapAnD;
        this.dapAnDung = dapAnDung;
    }

    protected DocHieuHTDoanVan(Parcel in) {
        idDH = in.readParcelable(DocHieu.class.getClassLoader());
        idCau = in.readInt();
        dapAnA = in.readString();
        dapAnB = in.readString();
        dapAnC = in.readString();
        dapAnD = in.readString();
        dapAnDung = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(idDH, flags);
        dest.writeInt(idCau);
        dest.writeString(dapAnA);
        dest.writeString(dapAnB);
        dest.writeString(dapAnC);
        dest.writeString(dapAnD);
        dest.writeString(dapAnDung);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DocHieuHTDoanVan> CREATOR = new Creator<DocHieuHTDoanVan>() {
        @Override
        public DocHieuHTDoanVan createFromParcel(Parcel in) {
            return new DocHieuHTDoanVan(in);
        }

        @Override
        public DocHieuHTDoanVan[] newArray(int size) {
            return new DocHieuHTDoanVan[size];
        }
    };

    public DocHieu getIdDH() {
        return idDH;
    }

    public void setIdDH(DocHieu idDH) {
        this.idDH = idDH;
    }

    public int getIdCau() {
        return idCau;
    }

    public void setIdCau(int idCau) {
        this.idCau = idCau;
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
        return "DocHieuHTDoanVan{" +
                "idDH=" + idDH +
                ", idCau=" + idCau +
                ", dapAnA='" + dapAnA + '\'' +
                ", dapAnB='" + dapAnB + '\'' +
                ", dapAnC='" + dapAnC + '\'' +
                ", dapAnD='" + dapAnD + '\'' +
                ", dapAnDung='" + dapAnDung + '\'' +
                '}';
    }
}
