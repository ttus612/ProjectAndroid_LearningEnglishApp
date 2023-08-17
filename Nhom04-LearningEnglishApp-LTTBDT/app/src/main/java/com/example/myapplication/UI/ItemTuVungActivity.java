package com.example.myapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.LuyenThiApdapter;
import com.example.myapplication.Adapter.TuVungApdapter;
import com.example.myapplication.DB.Database;
import com.example.myapplication.Entity.Book;
import com.example.myapplication.Entity.LuyenThi;
import com.example.myapplication.Entity.SoCau;
import com.example.myapplication.Entity.TuVung;
import com.example.myapplication.My_Interface.InterfaceClickItemLuyenThiListener;
import com.example.myapplication.My_Interface.InterfaceClickItemTuVungListener;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ItemTuVungActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TuVungApdapter tuVungApdapter;
    TextView tv_name_user,tvArrowBack;
    TextView tvTitleTuVung;
    EditText edtTimTuVung;
    Bundle bundle;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_tu_vung);
        tv_name_user = findViewById(R.id.tv_name_user);
        tvArrowBack = findViewById(R.id.tvArrowBack);
        tvTitleTuVung = findViewById(R.id.tvTitleTuVung);
        edtTimTuVung = findViewById(R.id.edtTimTuVung);
        db = new Database(ItemTuVungActivity.this);
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
        Book book  = (Book) bundle.get("tuVung_item");
        tvTitleTuVung.setText(book.getTitle());


        recyclerView  = findViewById(R.id.rcv_categoryLuyenThi);
        tuVungApdapter = new TuVungApdapter(ItemTuVungActivity.this,getListTuVung(book), new InterfaceClickItemTuVungListener() {
            @Override
            public void onClickItemTuVung(TuVung tuVung) {
                Toast.makeText(ItemTuVungActivity.this, "Chưa có", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickItem(Object object) {

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(tuVungApdapter);

        edtTimTuVung.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tuVungApdapter = new TuVungApdapter(ItemTuVungActivity.this,getListTuVungBySearch(book, s.toString()), new InterfaceClickItemTuVungListener() {
                    @Override
                    public void onClickItemTuVung(TuVung tuVung) {
                        Toast.makeText(ItemTuVungActivity.this, "Chưa có", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onClickItem(Object object) {

                    }
                });
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(tuVungApdapter);
            }
        });
    }

    private List<TuVung> getListTuVung(Book book) {
        List<TuVung> list = new ArrayList<>();
        try {
            Cursor c = db.query_hasresult("SELECT tiengAnh,tiengViet FROM TuVung t join ChuDe c on t.idChuDe = c.idChuDe WHERE tenChuDe = '"+book.getTitle()+"'");
            while(c.moveToNext()){
                String tiengAnh = c.getString(0);
                String tiengViet = c.getString(1);
                list.add(new TuVung(tiengAnh,tiengViet));
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private List<TuVung> getListTuVungBySearch(Book book,String keyWord) {
        List<TuVung> list = new ArrayList<>();
        try {
            Cursor c = db.query_hasresult("SELECT tiengAnh,tiengViet FROM TuVung t join ChuDe c on t.idChuDe = c.idChuDe WHERE tenChuDe = '"+book.getTitle()+"' and tiengAnh like '%"+keyWord+"%'");
            while(c.moveToNext()){
                String tiengAnh = c.getString(0);
                String tiengViet = c.getString(1);
                list.add(new TuVung(tiengAnh,tiengViet));
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}