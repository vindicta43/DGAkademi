package com.alperen.w_02.ui.main;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.alperen.w_02.R;
import com.alperen.w_02.databinding.ActivityMainBinding;
import com.alperen.w_02.models.ProductModel;
import com.google.android.material.badge.BadgeDrawable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private ActivityMainBinding binding;
    List<ProductModel> cartList = new ArrayList<>();

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

    public void setBadgeCount() {
        BadgeDrawable badge = binding.bottomNavView.getOrCreateBadge(R.id.cartFragment);

        if (cartList.size() > 0) {
            badge.setVisible(true);
            badge.setNumber(cartList.size());
        } else {
            badge.setVisible(false);
        }
    }
}