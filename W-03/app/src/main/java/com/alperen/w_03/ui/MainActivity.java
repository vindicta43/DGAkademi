package com.alperen.w_03.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alperen.w_03.R;
import com.alperen.w_03.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}