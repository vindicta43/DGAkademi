package com.alperen.w_02.ui.checkout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alperen.w_02.R;
import com.alperen.w_02.models.ProductModel;

import java.util.List;

/**
 * Created by Alperen on 20.08.2022.
 */
public class CheckoutRecyclerAdapter extends RecyclerView.Adapter<CheckoutRecyclerAdapter.ViewHolder>{
    private List<ProductModel> list;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        TextView tvCheckoutProductName = itemView.findViewById(R.id.tvCheckoutProductName);
        TextView tvCheckoutProductCount = itemView.findViewById(R.id.tvCheckoutProductCount);
        TextView tvCheckoutProductWeight = itemView.findViewById(R.id.tvCheckoutProductWeight);
        TextView tvCheckoutProductPrice = itemView.findViewById(R.id.tvCheckoutProductPrice);
    }

    public CheckoutRecyclerAdapter(List<ProductModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_checkout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvCheckoutProductName.setText(list.get(position).name);
        holder.tvCheckoutProductCount.setText(String.valueOf(list.get(position).count));
        holder.tvCheckoutProductWeight.setText(String.valueOf(list.get(position).weight));
        holder.tvCheckoutProductPrice.setText("$"+String.valueOf(list.get(position).price));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
