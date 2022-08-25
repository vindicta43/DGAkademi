package com.alperen.w_03.ui.signin;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.alperen.w_03.R;
import com.alperen.w_03.databinding.FragmentSignInBinding;
import com.alperen.w_03.repository.network.FirebaseRepository;
import com.alperen.w_03.repository.network.INetworkActions;
import com.alperen.w_03.utils.W03Util;

public class SignInFragment extends Fragment {
    FragmentSignInBinding binding;
    INetworkActions actions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(getLayoutInflater());

        actions = new INetworkActions() {
            @Override
            public void processing() {
                binding.loading.setVisibility(View.VISIBLE);
            }

            @Override
            public void success(String msgTitle, String msgBody) {
                binding.loading.setVisibility(View.GONE);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_signInFragment_to_homepageFragment);
            }

            @Override
            public void fail(String msgTitle, String msgBody) {
                binding.loading.setVisibility(View.GONE);
                W03Util.createDialog(msgTitle, msgBody, getContext());
            }
        };

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
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();

            if (!email.isEmpty() && !password.isEmpty()) {
                FirebaseRepository.signIn(email.trim(), password, actions);
            } else {
                if (email.isEmpty())
                    W03Util.setEditTextError(getResources().getString(R.string.field_required), binding.etEmail);

                if (password.isEmpty())
                    W03Util.setEditTextError(getResources().getString(R.string.field_required), binding.etPassword);
            }
        });

        binding.etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0)
                    W03Util.setEditTextError(null, binding.etEmail);
                else
                    W03Util.setEditTextError(getResources().getString(R.string.field_required), binding.etEmail);
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
                if (charSequence.length() > 0)
                    W03Util.setEditTextError(null, binding.etPassword);
                else
                    W03Util.setEditTextError(getResources().getString(R.string.field_required), binding.etPassword);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}