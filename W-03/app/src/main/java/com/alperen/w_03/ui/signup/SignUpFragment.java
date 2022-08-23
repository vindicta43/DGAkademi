package com.alperen.w_03.ui.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.alperen.w_03.R;
import com.alperen.w_03.databinding.FragmentSignUpBinding;

public class SignUpFragment extends Fragment {
    FragmentSignUpBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        binding.ibBack.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).popBackStack();
        });

        binding.btnSignUp.setOnClickListener(view -> {
            // TODO: sign up
        });
    }
}