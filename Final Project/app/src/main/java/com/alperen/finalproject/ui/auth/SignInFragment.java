package com.alperen.finalproject.ui.auth;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.alperen.finalproject.R;
import com.alperen.finalproject.databinding.FragmentSignInBinding;
import com.alperen.finalproject.utils.network.AppRepository;
import com.alperen.finalproject.utils.network.IAuthStatus;

public class SignInFragment extends Fragment {
    FragmentSignInBinding binding;
    IAuthStatus status;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(getLayoutInflater());

        // TODO: implement it
        status = new IAuthStatus() {
            @Override
            public void processing() {
                binding.loading.setVisibility(View.VISIBLE);
            }

            @Override
            public void success(String operation, String msg) {
                binding.loading.setVisibility(View.GONE);

                switch (operation) {
                    case "signIn":
                        getActivity().finish();
                        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_signInFragment_to_mainActivity);
                        break;

                    case "signUp":
                        new AlertDialog.Builder(getContext())
                                .setTitle("Success")
                                .setMessage(msg)
                                .setPositiveButton("Okay", (dialogInterface, i) -> {
                                })
                                .show();
                        break;
                }
            }

            @Override
            public void fail(String title, String msg) {
                binding.loading.setVisibility(View.GONE);
                new AlertDialog.Builder(getContext())
                        .setTitle(title)
                        .setMessage(msg)
                        .setPositiveButton("Okay", (dialogInterface, i) -> {
                        })
                        .show();
            }
        };

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        binding.btnSignIn.setOnClickListener(view -> {
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();

            if (!email.isEmpty() && !password.isEmpty()) {
                AppRepository.signIn(email.trim(), password, status);
            } else {
                binding.etEmail.setError("This field must be filled");
                binding.etPassword.setError("This field must be filled");
            }
        });

        binding.btnSignUp.setOnClickListener(view -> {
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();

            if (!email.isEmpty() && !password.isEmpty()) {
                AppRepository.signUp(email.trim(), password, status);
            } else {
                binding.etEmail.setError("This field must be filled");
                binding.etPassword.setError("This field must be filled");
            }
        });

        binding.tvForgotPassword.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_signInFragment_to_forgotPasswordFragment);
        });

        binding.etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0)
                    binding.etEmail.setError(null);
                else
                    binding.etEmail.setError("This field must be filled");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0)
                    binding.etPassword.setError(null);
                else
                    binding.etPassword.setError("This field must be filled");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}