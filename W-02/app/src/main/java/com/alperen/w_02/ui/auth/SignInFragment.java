package com.alperen.w_02.ui.auth;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.alperen.w_02.R;
import com.alperen.w_02.databinding.FragmentSignInBinding;
import com.alperen.w_02.utils.FirebaseRepository;
import com.alperen.w_02.utils.IAuthInterface;
import com.alperen.w_02.utils.W02Util;
import com.google.android.material.progressindicator.CircularProgressIndicator;

public class SignInFragment extends Fragment {
    private FragmentSignInBinding binding;
    private String fieldRequired = "";
    private IAuthInterface iAuthInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(getLayoutInflater());
        fieldRequired = getResources().getString(R.string.required_field);

        iAuthInterface = new IAuthInterface() {
            @Override
            public void processing() {
                binding.progress.setVisibility(View.VISIBLE);
            }

            @Override
            public void success(String action) {
                binding.progress.setVisibility(View.GONE);
                if (action.equals("signIn")) {
                    // TODO: Navigate to main activity
                    // Navigation.findNavController(binding.getRoot()).navigate(R.id);
                }

                if (action.equals("resetMail")) {
                    W02Util.setDialog(getContext(), "Success", "A reset mail has been sent");
                }
            }

            @Override
            public void fail(String title, String msg) {
                binding.progress.setVisibility(View.GONE);
                W02Util.setDialog(getContext(), title, msg);
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
                FirebaseRepository.signIn(email, password, iAuthInterface);
            } else {
                W02Util.setError(true, binding.layoutEmail, fieldRequired);
                W02Util.setError(true, binding.layoutPassword, fieldRequired);
            }
        });

        binding.tvForgotPassword.setOnClickListener(view -> {
            String email = binding.etEmail.getText().toString();
            if (!email.isEmpty()) {
                FirebaseRepository.sendResetMail(email, iAuthInterface);
            } else {
                W02Util.setDialog(getContext(), "Alert", "Email field must be filled");
            }
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
                W02Util.setError(charSequence.length() == 0, binding.layoutEmail, fieldRequired);
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
                W02Util.setError(charSequence.length() == 0, binding.layoutPassword, fieldRequired);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}