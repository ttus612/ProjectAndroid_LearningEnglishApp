package com.example.myapplication.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.SoCauAdapter;
import com.example.myapplication.DB.Database;
import com.example.myapplication.EnityDB.DocHieu;
import com.example.myapplication.EnityDB.DocHieuHTCau;
import com.example.myapplication.EnityDB.DocHieuHTDoanVan;
import com.example.myapplication.EnityDB.LuyenNhanh;
import com.example.myapplication.Entity.Book;
import com.example.myapplication.Entity.LuyenThi;
import com.example.myapplication.Entity.SoCau;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ItemTrangChuDocHieuActivity extends AppCompatActivity {

    TextView tv_name_user,tvArrowBack, textView;
    Bundle bundle;

    Button btnBatDau;
    private Spinner spinner;
    private SoCauAdapter soCauAdapter;


    Database db;
    int soLuongCauHoi = 0;
    Calendar calendar = Calendar.getInstance();
    Date currentTime = calendar.getTime();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    String formattedTime = sdf.format(currentTime);
    private ArrayList<DocHieu> listDe;
    private ArrayList<DocHieuHTCau> listDapAn;
    private String maLN;
    private ArrayList<DocHieu> listDeHtDoanVan;
    private ArrayList<DocHieuHTDoanVan> listDapAnHtDoanVan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_trang_chu_doc_hieu);
        tv_name_user = findViewById(R.id.tv_name_user);
        tvArrowBack = findViewById(R.id.tvArrowBack);
        textView = findViewById(R.id.textView);
        spinner = findViewById(R.id.spinnerSoCau);
        btnBatDau = findViewById(R.id.btnBatDau);
        db = new Database(ItemTrangChuDocHieuActivity.this);


        tvArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(ItemTrangChuDocHieuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        bundle  = getIntent().getExtras();
        if (bundle == null){
            return;
        }



        Book book  = (Book) bundle.get("docHieu_item");
        Integer id = (Integer) bundle.get("id_item");
        if (id == 2131165363) {
            soCauAdapter = new SoCauAdapter(this, R.layout.item_selected, getListSoCau());
            spinner.setAdapter(soCauAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    soLuongCauHoi = soCauAdapter.getItem(position).getSoCau();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            tv_name_user.setText(book.getTitle());
            textView.setText(" A word or pharse is missing in each of the sentences below, Four answer choices are given below each sentence. Select the best answer to complete the sentence. Then mark the letter (A), (B), (C) or (D) on your answer sheet.\n" +
                    " Một từ hoặc một cụm từ bị thiếu trong mỗi câu nói duới dây. Bạn lựa chọn đáp án được đưa ra duới mỗi câu nói. Hãy chọn đáp án đúng nhất để hoàn thành câu. Sau đó đánh dấu đáp án A, B, C hoặc D vào phần làm bài của bạn" );
            btnBatDau.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long diffInMinutes = 0;
                    try {
                        // Tạo một đối tượng Calendar mới
                        Calendar calendar = Calendar.getInstance();
                        // Cộng thêm 10 phút vào đối tượng Calendar
                        calendar.add(Calendar.MINUTE, soLuongCauHoi * 1);
                        // Lấy thời gian mới của đối tượng Calendar
                        Date newTime = calendar.getTime();
                        // Định dạng thời gian mới và in ra
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                        String formattedTimeNew = sdf.format(newTime);

                        long diffInMillis = newTime.getTime() - currentTime.getTime(); // Khoảng thời gian tính bằng milliseconds
                        diffInMinutes = TimeUnit.MILLISECONDS.toMillis(diffInMillis);

                        try {
                            Cursor cursor2 = db.query_hasresult("SELECT * FROM LuyenNhanh ORDER BY idLN DESC LIMIT 1");
                            maLN = "";
                            if (cursor2.getCount() != 0) {
                                while (cursor2.moveToNext()) {
                                    int id = cursor2.getInt(0);
                                    maLN += id;
                                }
                            } else {
                                maLN = "Không có dữ liệu";
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (db != null) {
                                db.close();
                            }
                        }
                        int idLN = Integer.parseInt(maLN) + 1;
                        String sqlLN = "INSERT INTO LuyenNhanh(idLN, soLuongCauHoiGioiHan, thoiGianBatDau, thoiGianKetThuc, idND) VALUES('" + idLN + "', '" + soLuongCauHoi + "', '" + formattedTime + "', '" + formattedTimeNew + "', "+MainActivity.getIdND()+")";
                        db.query_noresult(sqlLN);

//                    Lấy đọc hiểu:
                        try {
                            listDe = new ArrayList<DocHieu>();
                            listDapAn = new ArrayList<DocHieuHTCau>();
                            Cursor cursor = db.query_hasresult("SELECT * FROM DocHieu WHERE idDH <= 15 ORDER BY RANDOM() LIMIT " + soLuongCauHoi + "");
                            if (cursor.getCount() != 0) {
                                while (cursor.moveToNext()) {
                                    DocHieu docHieu;
                                    int id = cursor.getInt(0);
                                    String de = cursor.getString(1);
                                    docHieu = new DocHieu(id, de);
                                    listDe.add(docHieu);
                                    listDapAn = layDapAn(listDe);

                                }
                            } else {
                                listDe = null;
                            }

                            for (int i = 0; i < listDe.size(); i++) {
                                String sqlLNDH = "INSERT INTO LuyenNhanhDocHieu (idLN, idDH) VALUES ('" + idLN + "', '" + listDe.get(i).getIdDH() + "')";
                                db.query_noresult(sqlLNDH);
                            }

                            //IN RA ĐỂ COI
                            for (int i = 0; i < listDe.size(); i++) {
                                Log.d("TAG", "Đề " + (i + 1) + ": " + listDe.get(i));
                            }

                            for (int i = 0; i < listDapAn.size(); i++) {
                                Log.d("TAG", "ĐÁP ÁN " + (i + 1) + ": " + listDapAn.get(i));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (db != null) {
                                db.close();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (db != null) {
                            db.close();
                        }
                    }

                    // Tạo một Intent để chuyển từ Activity hiện tại sang Activity khác
                    Intent intent = new Intent(ItemTrangChuDocHieuActivity.this, DocHieuTestNhanhActivity.class);

                    // Tạo một ArrayList mới từ mảng listDe
                    ArrayList<DocHieuHTCau> arrayListDe = new ArrayList<DocHieuHTCau>();

                    for (DocHieuHTCau docHieuHTCau : listDapAn) {
                        // create a new DocHieu object using the data from the LuyenNhanh object
                        DocHieuHTCau docHieuHTCau2 = new DocHieuHTCau(new DocHieu(docHieuHTCau.getIdDH().getIdDH(), docHieuHTCau.getIdDH().getDe()), docHieuHTCau.getDapAnA(), docHieuHTCau.getDapAnB(), docHieuHTCau.getDapAnC(), docHieuHTCau.getDapAnD(), docHieuHTCau.getDapAnDung());
                        // add the new DocHieu object to the ArrayList
                        arrayListDe.add(docHieuHTCau2);
                    }

                    // Đưa ArrayList vào Intent
                    intent.putParcelableArrayListExtra("list_de", (ArrayList<? extends Parcelable>) arrayListDe);
                    intent.putExtra("myDataThoiGian", diffInMinutes);
                    // Khởi chạy Activity mới
                    startActivity(intent);
                }
            });
        }else if (id == 2131165366){

            soCauAdapter = new SoCauAdapter(this, R.layout.item_selected, getListSoCauHTCau());
            spinner.setAdapter(soCauAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    soLuongCauHoi = soCauAdapter.getItem(position).getSoCau();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            tv_name_user.setText(book.getTitle());
            textView.setText(" The test will give you a passage with lots of blanks, then you can find the best answer to fill in the blanks. The total number of questions in this section 4 sentences corresponds to 1 passage, each passage consists of 4 questions.\n" +
                    " Đề bài sẽ cho bạn một đoạn văn có nhiều chỗ trống, sau đó bạn có thể tìm đáp án phù hợp nhất để điền vào chỗ trống. Tổng số câu hỏi trong phần này 4 câu tương ứng với 1 đoạn văn, mỗi đoạn văn bao gồm 4 câu hỏi. ");
            btnBatDau.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long diffInMinutes = 0;
                    try {
                        // Tạo một đối tượng Calendar mới
                        Calendar calendar = Calendar.getInstance();
                        // Cộng thêm 10 phút vào đối tượng Calendar
                        calendar.add(Calendar.MINUTE, soLuongCauHoi * 2);
                        // Lấy thời gian mới của đối tượng Calendar
                        Date newTime = calendar.getTime();
                        // Định dạng thời gian mới và in ra
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                        String formattedTimeNew = sdf.format(newTime);

                        long diffInMillis = newTime.getTime() - currentTime.getTime(); // Khoảng thời gian tính bằng milliseconds
                        diffInMinutes = TimeUnit.MILLISECONDS.toMillis(diffInMillis);

                        try {
                            Cursor cursor2 = db.query_hasresult("SELECT * FROM LuyenNhanh ORDER BY idLN DESC LIMIT 1");
                            maLN = "";
                            if (cursor2.getCount() != 0) {
                                while (cursor2.moveToNext()) {
                                    int id = cursor2.getInt(0);
                                    maLN += id;
                                }
                            } else {
                                maLN = "Không có dữ liệu";
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (db != null) {
                                db.close();
                            }
                        }
                        int idLN = Integer.parseInt(maLN) + 1;
                        String sqlLN = "INSERT INTO LuyenNhanh(idLN, soLuongCauHoiGioiHan, thoiGianBatDau, thoiGianKetThuc, idND) VALUES('" + idLN + "', '" + soLuongCauHoi + "', '" + formattedTime + "', '" + formattedTimeNew + "', "+MainActivity.getIdND()+")";
                        db.query_noresult(sqlLN);

//                    Lấy đọc hiểu:
                        try {
                            listDeHtDoanVan = new ArrayList<DocHieu>();
                            listDapAnHtDoanVan = new ArrayList<DocHieuHTDoanVan>();
                            Log.d("TAG", "Số lượng câu hỏi: " + soLuongCauHoi);
                            Cursor cursor = db.query_hasresult("SELECT * FROM DocHieu WHERE idDH > 15 ORDER BY RANDOM() LIMIT " + soLuongCauHoi + "");
                            if (cursor.getCount() != 0) {
                                while (cursor.moveToNext()) {
                                    DocHieu docHieu;
                                    int id = cursor.getInt(0);
                                    String de = cursor.getString(1);
                                    docHieu = new DocHieu(id, de);
                                    listDeHtDoanVan.add(docHieu);
                                    listDapAnHtDoanVan = layDapAnHTDoanVan(listDeHtDoanVan);

                                }
                            } else {
                                listDeHtDoanVan = null;
                            }

                            for (int i = 0; i < listDeHtDoanVan.size(); i++) {
                                String sqlLNDH = "INSERT INTO LuyenNhanhDocHieu (idLN, idDH) VALUES ('" + idLN + "', '" + listDeHtDoanVan.get(i).getIdDH() + "')";
                                db.query_noresult(sqlLNDH);
                            }

//                            //IN RA ĐỂ COI
//                            for (int i = 0; i < listDeHtDoanVan.size(); i++) {
//                                Log.d("TAG", "Đề " + (i + 1) + ": " + listDeHtDoanVan.get(i));
//                            }
//
//                            for (int i = 0; i < listDapAnHtDoanVan.size(); i++) {
//                                Log.d("TAG", "ĐÁP ÁN " + (i + 1) + ": " + listDapAnHtDoanVan.get(i));
//                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (db != null) {
                                db.close();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // Tạo một Intent để chuyển từ Activity hiện tại sang Activity khác
                    Intent intent = new Intent(ItemTrangChuDocHieuActivity.this, DocHieuTestNhanhHTCauActivity.class);

                    // Tạo một ArrayList mới từ mảng listDe
                    ArrayList<DocHieuHTDoanVan> arrayListDeHtDoanVan = new ArrayList<DocHieuHTDoanVan>();

                    for (DocHieuHTDoanVan docHieuHTDoanVan : listDapAnHtDoanVan) {
                        // create a new DocHieu object using the data from the LuyenNhanh object
                        DocHieuHTDoanVan docHieuHTDoanVan2 = new DocHieuHTDoanVan(new DocHieu(docHieuHTDoanVan.getIdDH().getIdDH(), docHieuHTDoanVan.getIdDH().getDe()),docHieuHTDoanVan.getIdCau(), docHieuHTDoanVan.getDapAnA(), docHieuHTDoanVan.getDapAnB(), docHieuHTDoanVan.getDapAnC(), docHieuHTDoanVan.getDapAnD(), docHieuHTDoanVan.getDapAnDung());
                        // add the new DocHieu object to the ArrayList
                        arrayListDeHtDoanVan.add(docHieuHTDoanVan2);
                    }

                    // Đưa ArrayList vào Intent
                    intent.putParcelableArrayListExtra("list_de_ht_cau", (ArrayList<? extends Parcelable>) arrayListDeHtDoanVan);
                    intent.putExtra("myDataThoiGian", diffInMinutes);
                    // Khởi chạy Activity mới
                    startActivity(intent);
                }
            });
        }


    }

    private ArrayList<DocHieuHTDoanVan> layDapAnHTDoanVan(ArrayList<DocHieu> listDeHtDoanVan) {
        try {
            ArrayList<DocHieuHTDoanVan> listDapAnHtDoanVan = new ArrayList<DocHieuHTDoanVan>();
            DocHieuHTDoanVan docHieuHTDoanVan;
            for (int i = 0; i < listDeHtDoanVan.size(); i++) {
                Cursor cursor = db.query_hasresult("select * from DocHieuHTDoanVan WHERE idDH = "+listDeHtDoanVan.get(i).getIdDH()+"");
                String ds = "";
                if (cursor.getCount() != 0){
                    while (cursor.moveToNext()){
                        int idDH = cursor.getInt(0);
                        if (idDH == listDeHtDoanVan.get(i).getIdDH()){
                            int id = cursor.getInt(1);
                            String dapAnA = cursor.getString(2);
                            String dapAnB= cursor.getString(3);
                            String dapAnC = cursor.getString(4);
                            String dapAnD = cursor.getString(5);
                            String dapAnDung = cursor.getString(6);
                            docHieuHTDoanVan = new DocHieuHTDoanVan(new DocHieu(listDeHtDoanVan.get(i).getIdDH(),listDeHtDoanVan.get(i).getDe()),id, dapAnA, dapAnB, dapAnC, dapAnD, dapAnDung);

                            listDapAnHtDoanVan.add(docHieuHTDoanVan);
                        }else {
                            Toast.makeText(this, "Không có đáp án", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    listDapAnHtDoanVan = null;
                }
            }
            return listDapAnHtDoanVan;
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return null;
    }

    private ArrayList<DocHieuHTCau> layDapAn(ArrayList<DocHieu> listDe) {
        try {
            ArrayList<DocHieuHTCau> listDapAn = new ArrayList<DocHieuHTCau>();
            DocHieuHTCau docHieuHTCau;
            for (int i = 0; i < listDe.size(); i++) {
                Cursor cursor = db.query_hasresult("select * from DocHieuHTCau WHERE idDH = "+listDe.get(i).getIdDH()+"");
                String ds = "";
                if (cursor.getCount() != 0){
                    while (cursor.moveToNext()){
                        int idDH = cursor.getInt(0);
                        if (idDH == listDe.get(i).getIdDH()){
                            String dapAnA = cursor.getString(1);
                            String dapAnB= cursor.getString(2);
                            String dapAnC = cursor.getString(3);
                            String dapAnD = cursor.getString(4);
                            String dapAnDung = cursor.getString(5);
                            docHieuHTCau = new DocHieuHTCau(new DocHieu(listDe.get(i).getIdDH(),listDe.get(i).getDe()),dapAnA,dapAnB,dapAnC,dapAnD,dapAnDung);
                            listDapAn.add(docHieuHTCau);
                        }else {
                            Toast.makeText(this, "Không có đáp án", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    listDapAn = null;
                }
            }
            return listDapAn;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

    private List<SoCau> getListSoCauHTCau() {
        List<SoCau> list = new ArrayList<>();
        list.add(new SoCau(1));

        return list;
    }
}