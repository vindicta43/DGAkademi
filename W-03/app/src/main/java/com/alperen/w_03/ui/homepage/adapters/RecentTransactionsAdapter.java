package com.alperen.w_03.ui.homepage.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alperen.w_03.R;
import com.alperen.w_03.model.RecentTransactionsModel;

import java.util.List;

/**
 * Created by Alperen on 24.08.2022.
 */
public class RecentTransactionsAdapter extends RecyclerView.Adapter<RecentTransactionsAdapter.ViewHolder> {
    List<RecentTransactionsModel> list;

    public RecentTransactionsAdapter(List<RecentTransactionsModel> list) {
        this.list = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        TextView tvTransactionName = itemView.findViewById(R.id.tvTransactionName);
        TextView tvTransactionDate = itemView.findViewById(R.id.tvTransactionDate);
        TextView tvTransactionPrice = itemView.findViewById(R.id.tvTransactionPrice);

        // Dividers
        View dividerTop = itemView.findViewById(R.id.dividerTop);
        View dividerBottom = itemView.findViewById(R.id.dividerBottom);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recent_transactions, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTransactionName.setText(list.get(position).getTransactionName());
        holder.tvTransactionDate.setText(list.get(position).getTransactionDate());
        holder.tvTransactionPrice.setText(list.get(position).getTransactionPrice());

        // Clean top divider for first item
        if (position == 0)
            holder.dividerTop.setVisibility(View.GONE);

        // Clean bottom divider for last item
        if (position == list.size() - 1)
            holder.dividerBottom.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
