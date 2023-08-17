package com.example.myapplication.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.BookAdapter;
import com.example.myapplication.Adapter.CategoryAdapter;
import com.example.myapplication.DB.Database;
import com.example.myapplication.Entity.Book;
import com.example.myapplication.Entity.Category;
import com.example.myapplication.MainActivity;
import com.example.myapplication.My_Interface.InterfaceClickItemListener;
import com.example.myapplication.R;



import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    private RecyclerView recyclerViewTV;
    private BookAdapter categoryAdapterTV;

    private RecyclerView recyclerViewDH;
    private BookAdapter categoryAdapterDH;

    private RecyclerView recyclerViewNH;
    private BookAdapter categoryAdapterNH;
    private TextView tvSoTuVungDaLuu;
    private Button btnLuu;
    private Database db;





    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        db = new Database(HomeFragment.this.getContext());
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tvSoTuVungDaLuu = view.findViewById(R.id.tvSoTuVungLuu);
        tvSoTuVungDaLuu.setText(String.valueOf(getSoTuVungDaluu(MainActivity.getIdND())));
        //TỪ VỰNG
        recyclerViewTV  = view.findViewById(R.id.rcv_categoryTV);
        categoryAdapterTV = new BookAdapter(getListCategoryTV(), new InterfaceClickItemListener() {
            @Override
            public void onClickItem(Book book) {
                onClickGoToDetail(book);
            }

            @Override
            public void onClickItem(Object object) {

            }
        });
        LinearLayoutManager linearLayoutManagerTV = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        recyclerViewTV.setLayoutManager(linearLayoutManagerTV);
        categoryAdapterTV.setData(getListCategoryTV());
        recyclerViewTV.setAdapter(categoryAdapterTV);

        //NGHE HIỂU
        recyclerViewNH  = view.findViewById(R.id.rcv_categoryNH);
        categoryAdapterNH = new BookAdapter(getListCategoryNH(), new InterfaceClickItemListener() {
            @Override
            public void onClickItem(Book book) {
                Log.d("TAG", "onClickItem: "+book.getResoutceId());
                    onClickGoToDetailNH(book);
            }

            @Override
            public void onClickItem(Object object) {

            }
        });
        LinearLayoutManager linearLayoutManagerNH = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        recyclerViewNH.setLayoutManager(linearLayoutManagerNH);
        categoryAdapterNH.setData(getListCategoryNH());
        recyclerViewNH.setAdapter(categoryAdapterNH);

        // ĐỌC HIỂU
        recyclerViewDH  = view.findViewById(R.id.rcv_categoryDH);


        categoryAdapterDH = new BookAdapter(getListCategoryDH(), new InterfaceClickItemListener() {
            @Override
            public void onClickItem(Book book) {
                Log.d("TAG", "onClickItem: "+book.getResoutceId());
                    onClickGoToDetailDH(book);
            }

            @Override
            public void onClickItem(Object object) {

            }
        });
        LinearLayoutManager linearLayoutManagerDH = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        recyclerViewDH.setLayoutManager(linearLayoutManagerDH);
        categoryAdapterDH.setData(getListCategoryDH());
        recyclerViewDH.setAdapter(categoryAdapterDH);

        //ÔN TẬP TỪ VỰNG
        btnLuu = view.findViewById(R.id.btnOnTuVung);
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDetailOTTV(MainActivity.getIdND());
            }
        });
        return view;
    }

    private List<Book> getListCategoryDH() {
        List<Book> bookListDocHieu = new ArrayList<>();
        bookListDocHieu.add(new Book( R.drawable.dien_khuyet," Hoàn thành \ncâu"));
        bookListDocHieu.add(new Book( R.drawable.doc_hieu, "Hoàn thành \nđoạn văn"));
        return bookListDocHieu;
    }

    private List<Book> getListCategoryNH() {

        List<Book> bookListNgheHieu = new ArrayList<>();
        bookListNgheHieu.add(new Book( R.drawable.mo_ta_anh,"Mô Tả Tranh"));
        bookListNgheHieu.add(new Book( R.drawable.hoi_dap,"Hỏi Đáp"));
        return bookListNgheHieu;
    }

    private List<Book> getListCategoryTV() {
        List<Book> bookListTuVung = new ArrayList<>();
        try {
            Cursor c = db.query_hasresult("Select * from ChuDe");
            while(c.moveToNext()){
                String ten = c.getString(1);
                String hinhAnh = c.getString(2);
                int resourceId = this.getContext().getResources().getIdentifier(hinhAnh, "drawable", this.getContext().getPackageName());
                bookListTuVung.add(new Book(resourceId, ten));
            }
            return bookListTuVung;
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return null;
    }

    private int getSoTuVungDaluu(int idND){
        int sl=0;
        try {
            Cursor c = db.query_hasresult("SELECT idND, COUNT(DISTINCT idTuVung) AS soLuong FROM ChiTietTuVung GROUP BY idND HAVING idND = "+idND+"");
            while(c.moveToNext()){
                sl = c.getInt(1);
            }
            return sl;
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return 0;
    }

    private int getIdTheoUserName(String userName){
        int id=0;
        try {
            Cursor c = db.query_hasresult("SELECT idND FROM NguoiDung n JOIN TaiKhoan t on n.idTaiKhoan = t.userName WHERE userName = "+userName+"");
            while(c.moveToNext()){
                id = c.getInt(0);
            }
            return id;
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return 0;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void onClickGoToDetail(Book book){
        Intent intent = new Intent(getContext(), ItemTuVungActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("tuVung_item", book);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private void onClickGoToDetailNH(Book book){
        Intent intent = new Intent(getContext(), ItemTrangChuNgheHieuActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ngheHieu_item", book);
        bundle.putSerializable("id_item", book.getResoutceId());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void onClickGoToDetailDH(Book book){
        Intent intent = new Intent(getContext(), ItemTrangChuDocHieuActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("docHieu_item", book);
        bundle.putSerializable("id_item", book.getResoutceId());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void onClickGoToDetailOTTV(int idND){
        Intent intent = new Intent(getContext(), ItemOnTapTuVungActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ontaptuVung_item", idND);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}