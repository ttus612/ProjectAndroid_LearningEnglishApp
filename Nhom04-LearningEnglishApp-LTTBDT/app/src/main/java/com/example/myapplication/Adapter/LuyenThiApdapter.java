package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entity.LuyenThi;
import com.example.myapplication.My_Interface.InterfaceClickItemLuyenThiListener;
import com.example.myapplication.R;

import java.util.List;

public class LuyenThiApdapter extends RecyclerView.Adapter<LuyenThiApdapter.LuyenThiApdapterHolder>{
    private List<LuyenThi> luyenThis;
    private InterfaceClickItemLuyenThiListener interfaceClickItemLuyenThiListener;
    public LuyenThiApdapter(List<LuyenThi> luyenThis, InterfaceClickItemLuyenThiListener interfaceClickItemLuyenThiListener) {
        this.luyenThis = luyenThis;
        this.interfaceClickItemLuyenThiListener = interfaceClickItemLuyenThiListener;
    }

    @NonNull
    @Override
    public LuyenThiApdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_luyenthi, parent, false);
        return new LuyenThiApdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LuyenThiApdapterHolder holder, int position) {
        final LuyenThi luyenThi = luyenThis.get(position);
        if (luyenThi == null){
            return;
        }
        holder.tvTitle.setText(luyenThi.getTitle());
        holder.tvThoiGian.setText(luyenThi.getThoiGian() + "");
        holder.tvSoCau.setText(luyenThi.getSoCauHoi() + "");

        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceClickItemLuyenThiListener.onClickItemLuyenThi(luyenThi);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (luyenThis != null) {
            return luyenThis.size();
        }
        return 0;
    }

    public class LuyenThiApdapterHolder extends RecyclerView.ViewHolder{
        private androidx.cardview.widget.CardView layout_item;
        private TextView tvTitle;
        private TextView tvThoiGian;
        private TextView tvSoCau;

        public LuyenThiApdapterHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.textTitle);
            tvThoiGian = itemView.findViewById(R.id.textTgian);
            tvSoCau = itemView.findViewById(R.id.textCauHoi);
            layout_item = itemView.findViewById(R.id.layout_item_LT);
        }
    }
}
