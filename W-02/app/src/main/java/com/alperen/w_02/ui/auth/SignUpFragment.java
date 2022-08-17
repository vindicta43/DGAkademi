package com.alperen.w_02.ui.auth;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.alperen.w_02.R;
import com.alperen.w_02.databinding.FragmentSignUpBinding;
import com.alperen.w_02.utils.FirebaseRepository;
import com.alperen.w_02.utils.IAuthInterface;
import com.alperen.w_02.utils.W02Util;

public class SignUpFragment extends Fragment {
    private FragmentSignUpBinding binding;
    private String fieldRequired = "";
    private String passwordMatch = "";
    private IAuthInterface iAuthInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(getLayoutInflater());
        fieldRequired = getResources().getString(R.string.required_field);
        passwordMatch = getResources().getString(R.string.password_doesnt_match);

        iAuthInterface = new IAuthInterface() {
            @Override
            public void processing() {
                binding.progress.setVisibility(View.VISIBLE);
            }

            @Override
            public void success(String action) {
                binding.progress.setVisibility(View.GONE);
                if (action.equals("signUp")) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Success")
                            .setMessage("Account created successfully")
                            .setPositiveButton("Okay", (dialogInterface, i) -> {
                                Navigation.findNavController(container).popBackStack();
                            })
                            .show();
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

        binding.ibBack.setOnClickListener(view -> {
            Navigation.findNavController(view).popBackStack();
        });

        binding.btnSignUp.setOnClickListener(view -> {
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();
            String confirmPwd = binding.etPasswordConfirm.getText().toString();

            if (!email.isEmpty() && !password.isEmpty() && !confirmPwd.isEmpty()) {
                if (password.equals(confirmPwd)) {
                    FirebaseRepository.signUp(email, password, iAuthInterface);
                } else {
                    W02Util.setError(true, binding.layoutPassword, passwordMatch);
                    W02Util.setError(true, binding.layoutPasswordConfirm, passwordMatch);
                }
            } else {
                W02Util.setError(true, binding.layoutEmail, fieldRequired);
                W02Util.setError(true, binding.layoutPassword, fieldRequired);
                W02Util.setError(true, binding.layoutPasswordConfirm, fieldRequired);
            }
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
                if (charSequence.length() == 0) {
                    W02Util.setError(true, binding.layoutPassword, fieldRequired);
                } else {
                    W02Util.setError(false, binding.layoutEmail, fieldRequired);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etPasswordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                W02Util.setError(charSequence.length() == 0, binding.layoutPasswordConfirm, fieldRequired);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}