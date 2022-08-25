package com.alperen.w_03.ui.homepage.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alperen.w_03.R;
import com.alperen.w_03.model.CardModel;

import java.util.List;

/**
 * Created by Alperen on 24.08.2022.
 */
public class DashboardViewPagerAdapter extends RecyclerView.Adapter<DashboardViewPagerAdapter.ViewHolder> {
    private List<CardModel> list;

    public DashboardViewPagerAdapter(List<CardModel> list) {
        this.list = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        TextView tvCardNumber = itemView.findViewById(R.id.tvCardNumber);
        TextView tvCardHolderName = itemView.findViewById(R.id.tvCardHolderName);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvCardNumber.setText(list.get(position).getCardNumber());
        holder.tvCardHolderName.setText(list.get(position).getCardHolderName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
