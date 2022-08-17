package com.alperen.w_02.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.alperen.w_02.databinding.FragmentCartBinding;

public class CartFragment extends Fragment {
    FragmentCartBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}