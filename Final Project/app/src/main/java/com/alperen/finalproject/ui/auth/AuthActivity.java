package com.alperen.finalproject.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alperen.finalproject.R;
import com.alperen.finalproject.databinding.ActivityAuthBinding;

public class AuthActivity extends AppCompatActivity {
    ActivityAuthBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}