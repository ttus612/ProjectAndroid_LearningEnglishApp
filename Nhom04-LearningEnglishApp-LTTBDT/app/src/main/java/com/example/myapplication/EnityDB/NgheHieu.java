package com.example.myapplication.EnityDB;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class NgheHieu implements Parcelable {
    private int idNH;
    private String voice;

    public NgheHieu() {
    }

    public int getIdNH() {
        return idNH;
    }

    public void setIdNH(int idNH) {
        this.idNH = idNH;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public NgheHieu(int idNH) {
        this.idNH = idNH;
    }

    public NgheHieu(int idNH, String voice) {
        this.idNH = idNH;
        this.voice = voice;
    }

    protected NgheHieu(Parcel in) {
        idNH = in.readInt();
        voice = in.readString();
    }

    public static final Creator<NgheHieu> CREATOR = new Creator<NgheHieu>() {
        @Override
        public NgheHieu createFromParcel(Parcel in) {
            return new NgheHieu(in);
        }

        @Override
        public NgheHieu[] newArray(int size) {
            return new NgheHieu[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(idNH);
        dest.writeString(voice);
    }
}
