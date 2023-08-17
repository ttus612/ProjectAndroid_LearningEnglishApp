package com.example.myapplication.EnityDB;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class DocHieu implements Parcelable {
    private int idDH;
    private String de;

    public DocHieu() {
    }

    public DocHieu(int idDH) {
        this.idDH = idDH;
    }

    public DocHieu(int idDH, String de) {
        this.idDH = idDH;
        this.de = de;
    }

    protected DocHieu(Parcel in) {
        idDH = in.readInt();
        de = in.readString();
    }

    public static final Creator<DocHieu> CREATOR = new Creator<DocHieu>() {
        @Override
        public DocHieu createFromParcel(Parcel in) {
            return new DocHieu(in);
        }

        @Override
        public DocHieu[] newArray(int size) {
            return new DocHieu[size];
        }
    };

    public int getIdDH() {
        return idDH;
    }

    public void setIdDH(int idDH) {
        this.idDH = idDH;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    @Override
    public String toString() {
        return "DocHieu{" +
                "idDH=" + idDH +
                ", de='" + de + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(idDH);
        dest.writeString(de);
    }
}
