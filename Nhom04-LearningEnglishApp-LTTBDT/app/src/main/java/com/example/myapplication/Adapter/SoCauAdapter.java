package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Entity.SoCau;
import com.example.myapplication.R;

import java.util.List;

public class SoCauAdapter extends ArrayAdapter<SoCau> {
    public SoCauAdapter(@NonNull Context context, int resource, @NonNull List<SoCau> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected, parent, false);
        TextView tvSelected = convertView.findViewById(R.id.tvSelected);
        SoCau soCau = this.getItem(position);
        if (soCau != null) {
            tvSelected.setText(String.valueOf(soCau.getSoCau()));
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_so, parent, false);
        TextView tvSo = convertView.findViewById(R.id.tvSoCau);
        SoCau soCau = this.getItem(position);
        if (soCau != null) {
            tvSo.setText(String.valueOf(soCau.getSoCau()));
        }
        return convertView;
    }
}
