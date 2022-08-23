package com.alperen.w_03.ui.forgotpassword;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alperen.w_03.R;
import com.alperen.w_03.databinding.FragmentForgotPasswordBinding;

public class ForgotPasswordFragment extends Fragment {
    FragmentForgotPasswordBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentForgotPasswordBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        binding.ibBack.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).popBackStack();
        });

        binding.btnForgotPassword.setOnClickListener(view -> {
            // TODO: forgot password
        });
    }
}