package com.alperen.w_02.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alperen.w_02.R;

import java.util.List;

/**
 * Created by Alperen on 16.08.2022.
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    private List<String> list;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        ImageView ivProductImage = itemView.findViewById(R.id.ivProductImage);
        TextView tvProductName = itemView.findViewById(R.id.tvProductName);
        TextView tvProductWeight = itemView.findViewById(R.id.tvProductWeight);
        TextView tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
        ImageButton ibAddCart = itemView.findViewById(R.id.ibAddCart);
    }

    public MainRecyclerAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_main_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
