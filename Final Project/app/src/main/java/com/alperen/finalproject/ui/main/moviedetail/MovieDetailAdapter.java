package com.alperen.finalproject.ui.main.moviedetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alperen.finalproject.R;
import com.alperen.finalproject.models.CastModel;
import com.alperen.finalproject.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Alperen on 27.08.2022.
 */
public class MovieDetailAdapter extends RecyclerView.Adapter<MovieDetailAdapter.MovieViewHolder> {
    List<CastModel.Cast> list;

    public MovieDetailAdapter(List<CastModel.Cast> list) {
        this.list = list;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        ImageView ivCastImage = itemView.findViewById(R.id.ivCastImage);
        TextView tvCastName = itemView.findViewById(R.id.tvCastName);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rw_cast_single_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        String imagePath = Constants.POSTER_PATH + list.get(position).getProfilePath();
        Glide.with(holder.itemView).load(imagePath).into(holder.ivCastImage);
        holder.tvCastName.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
