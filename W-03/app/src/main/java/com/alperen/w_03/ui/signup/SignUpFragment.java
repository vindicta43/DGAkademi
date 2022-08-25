package com.alperen.w_03.ui.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.alperen.w_03.R;
import com.alperen.w_03.databinding.FragmentSignUpBinding;
import com.alperen.w_03.repository.network.FirebaseRepository;
import com.alperen.w_03.repository.network.INetworkActions;
import com.alperen.w_03.utils.W03Util;

public class SignUpFragment extends Fragment {
    FragmentSignUpBinding binding;
    INetworkActions actions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(getLayoutInflater());

        actions = new INetworkActions() {
            @Override
            public void processing() {
                binding.loading.setVisibility(View.VISIBLE);
            }

            @Override
            public void success(String msgTitle, String msgBody) {
                binding.loading.setVisibility(View.GONE);
                W03Util.createDialog(msgTitle, msgBody, getContext());
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

        binding.ibBack.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).popBackStack();
        });

        binding.btnSignUp.setOnClickListener(view -> {
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();
            String passwordVerify = binding.etPasswordVerify.getText().toString();

            if (!email.isEmpty() && !password.isEmpty() && !passwordVerify.isEmpty()) {
                if (password.equals(passwordVerify)) {
                    FirebaseRepository.signUp(email.trim(), password, actions);
                } else {
                    W03Util.setEditTextError(getResources().getString(R.string.passwords_dont_match), binding.etPassword);
                    W03Util.setEditTextError(getResources().getString(R.string.passwords_dont_match), binding.etPasswordVerify);
                }
            } else {
                if (email.isEmpty())
                    W03Util.setEditTextError(getResources().getString(R.string.field_required), binding.etEmail);

                if (password.isEmpty())
                    W03Util.setEditTextError(getResources().getString(R.string.field_required), binding.etPassword);

                if (passwordVerify.isEmpty())
                    W03Util.setEditTextError(getResources().getString(R.string.field_required), binding.etPasswordVerify);
            }
        });
    }
}