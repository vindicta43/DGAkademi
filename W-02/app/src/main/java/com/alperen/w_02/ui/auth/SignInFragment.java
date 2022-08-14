package com.alperen.w_02.ui.auth;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.alperen.w_02.R;
import com.alperen.w_02.databinding.FragmentSignInBinding;

public class SignInFragment extends Fragment {
    private FragmentSignInBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        binding.btnSignIn.setOnClickListener(view -> {
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();
            if (!email.isEmpty() && !password.isEmpty()) {
                // TODO: login işlemleri
            } else {
                binding.layoutEmail.setErrorEnabled(true);
                binding.layoutEmail.setError(getResources().getString(R.string.required_field));

                binding.layoutPassword.setErrorEnabled(true);
                binding.layoutPassword.setError(getResources().getString(R.string.required_field));
            }
        });

        binding.tvForgotPassword.setOnClickListener(view -> {
            // TODO: forgot password işlemi firebase ile
        });

        binding.tvSignUp.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_signInFragment_to_signUpFragment);
        });

        binding.etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    binding.layoutEmail.setErrorEnabled(true);
                    binding.layoutEmail.setError(getResources().getString(R.string.required_field));
                }
                else {
                    binding.layoutEmail.setErrorEnabled(false);
                    binding.layoutEmail.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    binding.layoutPassword.setErrorEnabled(true);
                    binding.layoutPassword.setError(getResources().getString(R.string.required_field));
                }
                else {
                    binding.layoutPassword.setErrorEnabled(false);
                    binding.layoutPassword.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}