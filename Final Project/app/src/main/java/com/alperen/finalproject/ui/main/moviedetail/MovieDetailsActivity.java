package com.alperen.finalproject.ui.main.moviedetail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alperen.finalproject.databinding.ActivityMovieDetailsBinding;
import com.alperen.finalproject.databinding.LayoutChipBinding;
import com.alperen.finalproject.models.CastModel;
import com.alperen.finalproject.models.GenresModel;
import com.alperen.finalproject.models.MovieDetailModel;
import com.alperen.finalproject.utils.Constants;
import com.alperen.finalproject.utils.MovieAdapter;
import com.alperen.finalproject.utils.network.AppRepository;
import com.alperen.finalproject.utils.network.IQueryStatus;
import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {
    ActivityMovieDetailsBinding binding;
    IQueryStatus status;

    List<MovieDetailModel> movieDetail = new ArrayList<>();
    List<CastModel.Cast> movieCast = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());

        status = new IQueryStatus() {
            @Override
            public void processing() {
                binding.loading.setVisibility(View.VISIBLE);
            }

            @Override
            public <T> void success(List<T> list) {
                binding.loading.setVisibility(View.GONE);

                movieDetail.addAll((List<MovieDetailModel>) list.get(0));
                movieCast.addAll((List<CastModel.Cast>) list.get(1));
                initPage();
            }

            @Override
            public void fail(String title, String msg) {
                binding.loading.setVisibility(View.GONE);
            }
        };

        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppRepository.getMovieDetails(getIntent().getIntExtra("movie", -1), status);
    }

    private void initPage() {
        MovieDetailAdapter adapter = new MovieDetailAdapter(movieCast);

        String imageUrl = Constants.POSTER_PATH + movieDetail.get(0).getBackdropPath();
        Glide.with(this).load(imageUrl).into(binding.ivMovieDetail);

        binding.content.rwMovieDetailCast.setAdapter(adapter);
        binding.content.rwMovieDetailCast.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        binding.content.tvMovieDetailName.setText(movieDetail.get(0).getTitle());
        binding.content.tvMovieDetailLanguage.setText(movieDetail.get(0).getSpokenLanguages().get(0).getName());
        binding.content.tvMovieDetailDesc.setText(movieDetail.get(0).getOverview());
        binding.content.tvMovieDetalRate.setText(String.format("%.2f", movieDetail.get(0).getVoteAverage()) + "/10 IMDb");

        int runtime = movieDetail.get(0).getRuntime();
        binding.content.tvMovieDetailTime.setText(runtime / 60 + "s " + runtime % 60 + "dk");

        String release = movieDetail.get(0).getReleaseDate().replace("-", "/");
        binding.content.tvMovieDetailReleaseDate.setText(release);

        binding.content.movieDetailChipGroup.removeAllViews();
        movieDetail.get(0).getGenres().forEach(singleGenre -> {
            String genreString = getGenreName(singleGenre);
            binding.content.movieDetailChipGroup.addView(createChip(genreString));
        });

        binding.content.ibMovieDetailBookmark.setOnClickListener(view -> {

        });
    }

    private Chip createChip(String genreString) {
        LayoutInflater li = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Chip tempChip = (Chip) LayoutChipBinding.inflate(li).getRoot();

        tempChip.setText(genreString);
        tempChip.setChipIcon(null);
        tempChip.setCheckable(false);
        tempChip.setClickable(false);

        return tempChip;
    }

    private String getGenreName(MovieDetailModel.Genre genre) {
        for (GenresModel.Genre i : MovieAdapter.genreList) {
            if (genre.getId().equals(i.getId()))
                return genre.getName();
        }
        return "unknown";
    }
}