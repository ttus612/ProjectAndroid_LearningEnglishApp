package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.UI.ItemTrangChuActivity;
import com.example.myapplication.Entity.Book;
import com.example.myapplication.Entity.Category;
import com.example.myapplication.My_Interface.InterfaceClickItemListener;
import com.example.myapplication.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context context;
    private List<Category> categoryList;

    public CategoryAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Category> list){
        this.categoryList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        if (category == null){
            return;
        }
        holder.tvNameCategory.setText(category.getNameCategory());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        holder.rcvBook.setLayoutManager(linearLayoutManager);


        BookAdapter bookAdapter = new BookAdapter(category.getBookList(), new InterfaceClickItemListener() {
            @Override
            public void onClickItem(Book book) {
                onClickGoToDetail(book);
            }

            @Override
            public void onClickItem(Object object) {

            }
        });
        bookAdapter.setData(category.getBookList());
        holder.rcvBook.setAdapter(bookAdapter);
    }

    @Override
    public int getItemCount() {
        if (categoryList != null){
            return categoryList.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNameCategory;
        private RecyclerView rcvBook;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameCategory = itemView.findViewById(R.id.tvName);
            rcvBook = itemView.findViewById(R.id.rvCategory);
        }
    }

    private void onClickGoToDetail(Book book){
        Intent intent = new Intent(context, ItemTrangChuActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("book_item", book);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
