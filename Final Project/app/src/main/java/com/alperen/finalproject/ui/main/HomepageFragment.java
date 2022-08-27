package com.alperen.finalproject.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alperen.finalproject.databinding.FragmentHomepageBinding;
import com.alperen.finalproject.models.GenreModel;
import com.alperen.finalproject.models.MovieModel;
import com.alperen.finalproject.utils.MovieAdapter;
import com.alperen.finalproject.utils.network.AppRepository;
import com.alperen.finalproject.utils.network.IQueryStatus;

import java.util.List;

public class HomepageFragment extends Fragment {
    FragmentHomepageBinding binding;
    IQueryStatus status;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomepageBinding.inflate(getLayoutInflater());

        status = new IQueryStatus() {
            @Override
            public void processing() {

            }

            @Override
            public <T> void success(List<T> list) {
                MovieAdapter adapter = new MovieAdapter((List<MovieModel>) list.get(0), (List<GenreModel.Genre>) list.get(1));
                binding.rvHome.setAdapter(adapter);
                binding.rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void fail(String title, String msg) {

            }
        };

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        AppRepository.getMovies(1, status);
    }
}