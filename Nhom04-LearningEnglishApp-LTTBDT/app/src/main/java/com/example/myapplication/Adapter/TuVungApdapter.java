package com.example.myapplication.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DB.Database;
import com.example.myapplication.Entity.Book;
import com.example.myapplication.Entity.LuyenThi;
import com.example.myapplication.Entity.TuVung;
import com.example.myapplication.MainActivity;
import com.example.myapplication.My_Interface.InterfaceClickItemLuyenThiListener;
import com.example.myapplication.My_Interface.InterfaceClickItemTuVungListener;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class TuVungApdapter extends RecyclerView.Adapter<TuVungApdapter.TuVungApdapterHolder>{
    private List<TuVung> tuVungs;
    private Database db;

    private InterfaceClickItemTuVungListener interfaceClickItemTuVungListener;

    public TuVungApdapter(Context context,List<TuVung> tuVungs, InterfaceClickItemTuVungListener interfaceClickItemTuVungListener) {
        this.tuVungs = tuVungs;
        this.interfaceClickItemTuVungListener = interfaceClickItemTuVungListener;
        db = new Database(context);
    }

    @NonNull
    @Override
    public TuVungApdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tuvung, parent, false);
        return new TuVungApdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TuVungApdapterHolder holder, int position) {
        final TuVung tuVung = tuVungs.get(position);
        if (tuVung == null){
            return;
        }
        holder.tvTiengAnh.setText(tuVung.getTiengAnh());
        holder.tvTiengViet.setText(tuVung.getTiengViet());
        holder.chkLuuTuVung.setChecked(checkTuVungTrongSoTay(tuVung.getTiengAnh(),MainActivity.getIdND()));



        holder.layout_item_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceClickItemTuVungListener.onClickItemTuVung(tuVung);
            }
        });

        holder.chkLuuTuVung.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    luuTuVungVaoSoTay(holder.chkLuuTuVung.getContext(), MainActivity.getIdND(),getIdTheoTiengAnh(tuVung.getTiengAnh()));
                } else{
                    xoaTuVungKhoiSoTay(holder.chkLuuTuVung.getContext(), MainActivity.getIdND(),getIdTheoTiengAnh(tuVung.getTiengAnh()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (tuVungs != null) {
            return tuVungs.size();
        }
        return 0;
    }

    private void luuTuVungVaoSoTay(Context context, int idND, int idTV) {
        try {
            db.query_noresult("insert into ChiTietTuVung(idND,idTuVung) values ("+idND+", "+idTV+")");
            Toast.makeText(context, "Đã lưu vào sổ tay", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void xoaTuVungKhoiSoTay(Context context, int idND, int idTV) {
        try {
            db.query_noresult("delete from ChiTietTuVung where idND = "+idND+" and idTuVung = "+idTV+"");
            Toast.makeText(context, "Đã xóa khỏi sổ tay", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean checkTuVungTrongSoTay(String tiengAnh, int idND) {
        try {
            Cursor c = db.query_hasresult("SELECT tiengAnh FROM ChiTietTuVung c join TuVung t on c.idTuVung = t.idTuVung  WHERE idND = "+idND+"");
            while(c.moveToNext()){
                String a = c.getString(0);
                if (a.equalsIgnoreCase(tiengAnh))
                    return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private int getIdTheoTiengAnh(String tiengAnh){
        int id=0;
        try {
            Cursor c = db.query_hasresult("SELECT idTuVung FROM TuVung WHERE tiengAnh = '"+tiengAnh+"'");
            while(c.moveToNext()){
                id = c.getInt(0);
            }
            return id;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public class TuVungApdapterHolder extends RecyclerView.ViewHolder{
        private androidx.cardview.widget.CardView layout_item_TV;
        private TextView tvTiengAnh;
        private TextView tvTiengViet;
        private CheckBox chkLuuTuVung;


        public TuVungApdapterHolder(@NonNull View itemView) {
            super(itemView);
            tvTiengAnh = itemView.findViewById(R.id.textTiengAnh);
            tvTiengViet = itemView.findViewById(R.id.textViet);
            layout_item_TV = itemView.findViewById(R.id.layout_item_TV);
            chkLuuTuVung = itemView.findViewById(R.id.chkLuuTuVung);

        }
    }
}
