package com.alperen.w_03.ui.homepage.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.alperen.w_03.R;
import com.alperen.w_03.model.UpcomingPaymentsModel;

import java.util.List;

/**
 * Created by Alperen on 24.08.2022.
 */
public class UpcomingPaymentsAdapter extends RecyclerView.Adapter<UpcomingPaymentsAdapter.ViewHolder> {
    List<UpcomingPaymentsModel> list;

    public UpcomingPaymentsAdapter(List<UpcomingPaymentsModel> list) {
        this.list = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        TextView tvPaymentName = itemView.findViewById(R.id.tvPaymentName);
        TextView tvPaymentType = itemView.findViewById(R.id.tvPaymentType);
        TextView tvPaymentPrice = itemView.findViewById(R.id.tvPaymentPrice);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_upcoming_payments_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvPaymentName.setText(list.get(position).getPaymentName());
        holder.tvPaymentType.setText(list.get(position).getPaymentType());
        holder.tvPaymentPrice.setText(list.get(position).getPaymentPrice());

        // If position at the last item, give margin for equal spacing
        if (position != list.size() - 1) {
            ConstraintLayout.LayoutParams params =
                    new ConstraintLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMarginEnd(32);
            holder.itemView.setLayoutParams(params);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
