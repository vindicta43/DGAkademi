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
import com.alperen.w_02.models.ProductModel;

import java.util.List;

/**
 * Created by Alperen on 17.08.2022.
 */
public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder> {
    private List<ProductModel> list;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        ImageView ivCartImage = itemView.findViewById(R.id.ivCartImage);
        TextView tvCartItemName = itemView.findViewById(R.id.tvCartItemName);
        ImageButton ibCartMinus = itemView.findViewById(R.id.ibCartMinus);
        ImageButton ibCartPlus = itemView.findViewById(R.id.ibCartPlus);
        TextView tvCartQuantity = itemView.findViewById(R.id.tvCartQuantity);
        TextView tvCartItemPrice = itemView.findViewById(R.id.tvCartItemPrice);
    }

    public CartRecyclerAdapter(List<ProductModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_cart_item, parent, false);
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
