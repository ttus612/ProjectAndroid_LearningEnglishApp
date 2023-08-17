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

public class ItemOnTapTuVungActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TuVungApdapter tuVungApdapter;
    TextView tv_name_user,tvArrowBack;
    EditText edtTimTuVung;
    Bundle bundle;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_ontaptuvung);
        tv_name_user = findViewById(R.id.tv_name_user);
        tvArrowBack = findViewById(R.id.tvArrowBack);;
        edtTimTuVung = findViewById(R.id.edtTimTuVung);
        db = new Database(ItemOnTapTuVungActivity.this);


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
        int idND  = (int) bundle.get("ontaptuVung_item");


        recyclerView  = findViewById(R.id.rcv_categoryLuyenThi);
        tuVungApdapter = new TuVungApdapter(ItemOnTapTuVungActivity.this,getListTuVung(idND), new InterfaceClickItemTuVungListener() {
            @Override
            public void onClickItemTuVung(TuVung tuVung) {
                Toast.makeText(ItemOnTapTuVungActivity.this, "Chưa có", Toast.LENGTH_SHORT).show();
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
                tuVungApdapter = new TuVungApdapter(ItemOnTapTuVungActivity.this,getListTuVungBySearch(idND, s.toString()), new InterfaceClickItemTuVungListener() {
                    @Override
                    public void onClickItemTuVung(TuVung tuVung) {
                        Toast.makeText(ItemOnTapTuVungActivity.this, "Chưa có", Toast.LENGTH_SHORT).show();
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

    private List<TuVung> getListTuVung(int idND) {
        List<TuVung> list = new ArrayList<>();
        try {
            Cursor c = db.query_hasresult("SELECT tiengAnh, tiengViet FROM ChiTietTuVung c join TuVung t on c.idTuVung = t.idTuVung WHERE idND = "+idND+"");
            while(c.moveToNext()){
                String tiengAnh = c.getString(0);
                String tiengViet = c.getString(1);
                list.add(new TuVung(tiengAnh,tiengViet));
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return null;
    }

    private List<TuVung> getListTuVungBySearch(int idND,String keyWord) {
        List<TuVung> list = new ArrayList<>();
        try {
            Cursor c = db.query_hasresult("SELECT tiengAnh, tiengViet FROM ChiTietTuVung c join TuVung t on c.idTuVung = t.idTuVung WHERE idND = "+idND+" and tiengAnh like '%"+keyWord+"%'");
            while(c.moveToNext()){
                String tiengAnh = c.getString(0);
                String tiengViet = c.getString(1);
                list.add(new TuVung(tiengAnh,tiengViet));
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return null;
    }
}