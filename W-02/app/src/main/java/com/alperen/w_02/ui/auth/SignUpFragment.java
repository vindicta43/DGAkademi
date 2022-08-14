package com.alperen.w_02.ui.auth;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alperen.w_02.R;
import com.alperen.w_02.databinding.FragmentSignUpBinding;

public class SignUpFragment extends Fragment {
    private FragmentSignUpBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}