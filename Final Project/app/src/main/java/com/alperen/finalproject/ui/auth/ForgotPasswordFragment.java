package com.alperen.finalproject.ui.auth;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alperen.finalproject.R;
import com.alperen.finalproject.databinding.FragmentForgotPasswordBinding;
import com.alperen.finalproject.utils.network.AppRepository;
import com.alperen.finalproject.utils.network.IAuthStatus;

public class ForgotPasswordFragment extends Fragment {
    FragmentForgotPasswordBinding binding;
    IAuthStatus status;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentForgotPasswordBinding.inflate(getLayoutInflater());

        status = new IAuthStatus() {
            @Override
            public void processing() {
                binding.loading.setVisibility(View.VISIBLE);
            }

            @Override
            public void success(String operation, String msg) {
                binding.loading.setVisibility(View.GONE);
                new AlertDialog.Builder(getContext())
                        .setTitle("Success")
                        .setMessage(msg)
                        .setPositiveButton("Okay", (dialogInterface, i) -> {
                            Navigation.findNavController(binding.getRoot()).popBackStack();
                        })
                        .show();
            }

            @Override
            public void fail(String title, String msg) {
                binding.loading.setVisibility(View.GONE);
                new AlertDialog.Builder(getContext())
                        .setTitle(title)
                        .setMessage(msg)
                        .setPositiveButton("Okay", (dialogInterface, i) -> {})
                        .show();
            }
        };

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        binding.btnBack.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).popBackStack();
        });

        binding.btnForgotPassword.setOnClickListener(view -> {
            String email = binding.etEmail.getText().toString();

            if (!email.isEmpty()) {
                AppRepository.sendResetEmail(email.trim(), status);
            } else {
                binding.etEmail.setError("This field must be filled");
            }
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
    }
}