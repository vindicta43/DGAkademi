package com.alperen.finalproject.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alperen.finalproject.R;
import com.alperen.finalproject.databinding.LayoutChipBinding;
import com.alperen.finalproject.models.GenreModel;
import com.alperen.finalproject.models.MovieModel;
import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

/**
 * Created by Alperen on 26.08.2022.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    List<MovieModel> list;
    List<GenreModel.Genre> genreList;

    public MovieAdapter(List<MovieModel> list, List<GenreModel.Genre> genreList) {
        this.list = list;
        this.genreList = genreList;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        ImageView ivMovieImage = itemView.findViewById(R.id.ivMovieImage);
        TextView tvMovieName = itemView.findViewById(R.id.tvMovieName);
        TextView tvMoviePoint = itemView.findViewById(R.id.tvMoviePoint);
        TextView tvMovieLength = itemView.findViewById(R.id.tvMovieLength);
        ChipGroup movieChipGroup = itemView.findViewById(R.id.movieChipGroup);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rw_home_single_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        String imagePath = Constants.POSTER_PATH + list.get(position).getPosterPath();
        Glide.with(holder.itemView).load(imagePath).into(holder.ivMovieImage);

        holder.tvMovieName.setText(list.get(position).getTitle());
        // TODO: daha sonra bak
        //  holder.tvMovieLength

        Log.d("genreId", "item pos: " + (position + 1) + list.get(position).getGenreIds().toString());

        holder.movieChipGroup.removeAllViews();
        list.get(position).getGenreIds().forEach(genreId -> {
            String genreString = getGenreName(genreId);
            holder.movieChipGroup.addView(createChip(genreString, holder));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private Chip createChip(String genreString, MovieViewHolder holder) {
        LayoutInflater li = (LayoutInflater) holder.itemView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Chip tempChip = (Chip) LayoutChipBinding.inflate(li).getRoot();

        tempChip.setText(genreString);
        tempChip.setChipIcon(null);
        tempChip.setCheckable(false);
        tempChip.setClickable(false);

        return tempChip;
    }

    private String getGenreName(int genreId) {
        for (GenreModel.Genre genre : genreList) {
            if (genre.getId().equals(genreId))
                return genre.getName();
        }
        return "unknown";
    }
}
