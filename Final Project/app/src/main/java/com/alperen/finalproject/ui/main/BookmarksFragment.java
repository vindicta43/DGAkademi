package com.alperen.finalproject.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alperen.finalproject.R;
import com.alperen.finalproject.databinding.FragmentBookmarksBinding;

public class BookmarksFragment extends Fragment {
    FragmentBookmarksBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBookmarksBinding.inflate(getLayoutInflater());
        MainActivity.binding.tvPageTitle.setText("Favoriler");
        return binding.getRoot();
    }
}