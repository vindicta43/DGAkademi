package com.alperen.w_03.ui.forgotpassword;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.alperen.w_03.R;
import com.alperen.w_03.databinding.FragmentForgotPasswordBinding;
import com.alperen.w_03.repository.network.FirebaseRepository;
import com.alperen.w_03.repository.network.INetworkActions;
import com.alperen.w_03.utils.W03Util;

public class ForgotPasswordFragment extends Fragment {
    FragmentForgotPasswordBinding binding;
    INetworkActions actions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentForgotPasswordBinding.inflate(getLayoutInflater());

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

        binding.btnForgotPassword.setOnClickListener(view -> {
            String email = binding.etEmail.getText().toString();

            if (!email.isEmpty()) {
                FirebaseRepository.sendResetEmail(email.trim(), actions);
            } else {
                W03Util.setEditTextError(getResources().getString(R.string.field_required), binding.etEmail);
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
    }
}