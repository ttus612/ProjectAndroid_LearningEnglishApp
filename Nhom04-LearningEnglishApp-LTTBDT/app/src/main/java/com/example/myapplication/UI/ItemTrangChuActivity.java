package com.example.myapplication.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.SoCauAdapter;
import com.example.myapplication.Entity.Book;
import com.example.myapplication.Entity.SoCau;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ItemTrangChuActivity extends AppCompatActivity {

    TextView tv_name_user,tvArrowBack;
    Bundle bundle;
    Button  button;
    private Spinner spinner;
    private SoCauAdapter soCauAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_trangchu);
        tv_name_user = findViewById(R.id.tv_name_user);
        tvArrowBack = findViewById(R.id.tvArrowBack);
        spinner = findViewById(R.id.spinnerSoCau);
        button = findViewById(R.id.button);

        tvArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bundle  = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        Book book  = (Book) bundle.get("book_item");
        tv_name_user.setText(book.getTitle());

        soCauAdapter = new SoCauAdapter(this, R.layout.item_selected, getListSoCau());
        spinner.setAdapter(soCauAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ItemTrangChuActivity.this, soCauAdapter.getItem(position).getSoCau()+"", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemTrangChuActivity.this, TestNhanh_MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<SoCau> getListSoCau() {
        List<SoCau> list = new ArrayList<>();
        list.add(new SoCau(1));
        list.add(new SoCau(2));
        list.add(new SoCau(3));
        list.add(new SoCau(4));
        list.add(new SoCau(5));
        list.add(new SoCau(6));
        list.add(new SoCau(7));
        list.add(new SoCau(8));
        list.add(new SoCau(9));
        list.add(new SoCau(10));
        return list;
    }
}