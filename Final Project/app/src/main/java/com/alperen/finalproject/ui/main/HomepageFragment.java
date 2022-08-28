package com.alperen.finalproject.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alperen.finalproject.databinding.FragmentHomepageBinding;
import com.alperen.finalproject.models.GenresModel;
import com.alperen.finalproject.models.MovieDetailModel;
import com.alperen.finalproject.models.MovieModel;
import com.alperen.finalproject.utils.MovieAdapter;
import com.alperen.finalproject.utils.network.AppRepository;
import com.alperen.finalproject.utils.network.IQueryStatus;

import java.util.ArrayList;
import java.util.List;

public class HomepageFragment extends Fragment {
    FragmentHomepageBinding binding;
    IQueryStatus status;
    private static int PAGE_NUM = 1;
    private static List<MovieModel> movieList = new ArrayList<>();
    private static List<GenresModel.Genre> genreList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomepageBinding.inflate(getLayoutInflater());

        status = new IQueryStatus() {
            @Override
            public void processing() {
                startAnim();
            }

            @Override
            public <T> void success(List<T> list) {
                stopAnim();

                movieList.addAll((List<MovieModel>) list.get(0));

                // Genrelist init for once
                if (genreList.isEmpty())
                    genreList.addAll((List<GenresModel.Genre>) list.get(1));

                if (binding.rvHome.getAdapter() == null) {
                    MovieAdapter adapter = new MovieAdapter(movieList, genreList);
                    binding.rvHome.setAdapter(adapter);
                    binding.rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
                } else {
                    binding.rvHome.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void fail(String title, String msg) {
                stopAnim();
            }
        };

        MainActivity.binding.tvPageTitle.setText("Popüler");
        AppRepository.getMovies(1, status);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        binding.rvHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    // Wait 0.5 second for better user experience
                    new Handler().postDelayed(() -> {
                        AppRepository.getMovies(++PAGE_NUM, status);
                    }, 250);

                }
            }
        });
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