package com.alperen.finalproject.ui.main;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.alperen.finalproject.R;
import com.alperen.finalproject.databinding.ActivityMainBinding;
import com.alperen.finalproject.models.PaginationModel;
import com.alperen.finalproject.utils.Constants;
import com.alperen.finalproject.utils.network.MovieRepository;
import com.alperen.finalproject.utils.network.MovieService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends FragmentActivity {
    public static ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();

        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerMain);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        NavigationUI.setupWithNavController(bottomNav, navController);
    }
}