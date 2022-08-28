package com.alperen.finalproject.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alperen.finalproject.databinding.FragmentBookmarksBinding;
import com.alperen.finalproject.models.MovieDetailModel;
import com.alperen.finalproject.models.MovieModel;
import com.alperen.finalproject.utils.MovieAdapter;
import com.alperen.finalproject.utils.network.AppRepository;
import com.alperen.finalproject.utils.network.IQueryStatus;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BookmarksFragment extends Fragment {
    FragmentBookmarksBinding binding;
    IQueryStatus status;
    List<MovieModel> debugList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBookmarksBinding.inflate(getLayoutInflater());
        MainActivity.binding.tvPageTitle.setText("Favoriler");

        status = new IQueryStatus() {
            @Override
            public void processing() {
                startAnim();
            }

            @Override
            public <T> void success(List<T> list) {
                stopAnim();

                MovieAdapter adapter;
                List<MovieModel> result = movieDetailToMovieModel(list);
                debugList.clear();
                debugList.addAll(result);

                // Init recyclerview adatper for once
                if (debugList.size() == 1) {
                    adapter = new MovieAdapter(debugList, MovieAdapter.genreList);
                    binding.rvBookmarks.setAdapter(adapter);
                    binding.rvBookmarks.setLayoutManager(new LinearLayoutManager(getContext()));
                } else {
                    binding.rvBookmarks.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void fail(String title, String msg) {
                stopAnim();
            }
        };

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        AppRepository.getUserBookmarks().observe(getViewLifecycleOwner(), integers -> {
            if (integers.isEmpty()) {
                new AlertDialog.Builder(getContext())
                        .setMessage("Bookmarks are empty")
                        .setPositiveButton("Okay", (dialogInterface, i) -> {
                        })
                        .show();
            } else {
                AppRepository.getBookmarkMovies(integers, status);
            }
        });
    }

    private <T> List<MovieModel> movieDetailToMovieModel(List<T> list) {
        List<MovieModel> result = new ArrayList<>();

        for (MovieDetailModel singleItem : (List<MovieDetailModel>) list) {
            List<Integer> genreList = new ArrayList<>();

            for (MovieDetailModel.Genre i : singleItem.getGenres()) {
                genreList.add(i.getId());
            }

            Number number = 0;
            NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
            String voteString = String.format("%.1f", singleItem.getVoteAverage());
            try {
                number = format.parse(voteString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            MovieModel model = new MovieModel(
                    singleItem.getId(),
                    genreList,
                    singleItem.getPosterPath(),
                    singleItem.getTitle(),
                    number.doubleValue(),
                    singleItem.getVoteCount()
            );

            result.add(model);
        }

        return result;
    }

    private void startAnim() {
        binding.shimmerFrame.setVisibility(View.VISIBLE);
        binding.shimmerFrame.startShimmer();
    }

    private void stopAnim() {
        binding.shimmerFrame.setVisibility(View.GONE);
        binding.shimmerFrame.stopShimmer();
    }
}