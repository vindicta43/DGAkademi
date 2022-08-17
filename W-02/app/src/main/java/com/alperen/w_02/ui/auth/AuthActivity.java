package com.alperen.w_02.ui.auth;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.alperen.w_02.databinding.ActivityAuthBinding;


public class AuthActivity extends FragmentActivity {
    private ActivityAuthBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}