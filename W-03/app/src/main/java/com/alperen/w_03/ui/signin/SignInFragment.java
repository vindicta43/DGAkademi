package com.alperen.w_03.ui.signin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alperen.w_03.R;
import com.alperen.w_03.databinding.FragmentSignInBinding;

public class SignInFragment extends Fragment {
    FragmentSignInBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        binding.tvForgotPassword.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_signInFragment_to_forgotPasswordFragment);
        });

        binding.tvSignUp.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_signInFragment_to_signUpFragment);
        });

        binding.btnSignIn.setOnClickListener(view -> {
            // TODO: auth user
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_signInFragment_to_homepageFragment);
        });
    }
}