package com.alperen.w_02.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alperen.w_02.R;
import com.alperen.w_02.models.ProductModel;
import com.alperen.w_02.ui.main.MainActivity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

/**
 * Created by Alperen on 16.08.2022.
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    private List<ProductModel> list;
    IRecycleViewEvent event;

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

    public MainRecyclerAdapter(List<ProductModel> list, IRecycleViewEvent event) {
        this.list = list;
        this.event = event;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_main_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvProductName.setText(list.get(position).name);
        holder.tvProductPrice.setText(String.valueOf(list.get(position).price));
        holder.tvProductWeight.setText(String.valueOf(list.get(position).weight));

        StorageReference reference = FirebaseStorage.getInstance().getReference(list.get(position).image);
                GlideApp.with(holder.itemView.getContext())
                        .load(reference)
                        .into(holder.ivProductImage);

        holder.ibAddCart.setOnClickListener(view -> {
            event.addItemToCart(list.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
