package com.alperen.w_03.ui.homepage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alperen.w_03.R;
import com.alperen.w_03.databinding.FragmentHomepageBinding;

public class HomepageFragment extends Fragment {
    FragmentHomepageBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomepageBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}