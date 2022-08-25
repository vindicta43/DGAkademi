package com.alperen.w_03.ui.splash;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.alperen.w_03.R;
import com.alperen.w_03.databinding.FragmentSplashBinding;
import com.google.firebase.auth.FirebaseAuth;

public class SplashFragment extends Fragment {
    FragmentSplashBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSplashBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            if (FirebaseAuth.getInstance().getCurrentUser() != null)
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_splashFragment_to_homepageFragment);
            else
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_splashFragment_to_loginFragment);
        }, 2000);
    }
}