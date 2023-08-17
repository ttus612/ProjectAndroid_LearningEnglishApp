package com.example.myapplication.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.Entity.LuyenThi;
import com.example.myapplication.R;

public class ItemLuyenThiActivity extends AppCompatActivity {
    TextView tvTitle;
    TextView tvSoCau;
    TextView tvThoiGian;

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_luyen_thi);
        tvTitle = findViewById(R.id.tvTitle);
        tvSoCau = findViewById(R.id.tvSoCau);
        tvThoiGian = findViewById(R.id.tvThoiGian);

        bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        LuyenThi luyenThi = (LuyenThi) bundle.get("luyenThi_item");
        tvTitle.setText(luyenThi.getTitle());
        tvSoCau.setText("Câu hỏi: " + luyenThi.getSoCauHoi());
        tvThoiGian.setText("Thời gian: " + luyenThi.getThoiGian() + " phút");
    }
}