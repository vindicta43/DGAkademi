package com.alperen.w_02.ui.main;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.alperen.w_02.R;
import com.alperen.w_02.databinding.ActivityMainBinding;

public class MainActivity extends FragmentActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerMain);
        NavigationUI.setupWithNavController(binding.bottomNavView, navHostFragment.getNavController());
    }
}