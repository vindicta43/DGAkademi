package com.alperen.w_01.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.alperen.w_01.R;
import com.alperen.w_01.network.FirebaseInstance;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnLogin.setOnClickListener(view -> {
            String emailText = etEmail.getText().toString();
            String passwordText = etPassword.getText().toString();

            if (!emailText.isEmpty() && !passwordText.isEmpty()) {
                FirebaseInstance.login(emailText, passwordText, this);
            } else {
                setError(emailText, passwordText);
            }
        });

        btnRegister.setOnClickListener(view -> {
            String emailText = etEmail.getText().toString();
            String passwordText = etPassword.getText().toString();

            if (!emailText.isEmpty() && !passwordText.isEmpty()) {
                FirebaseInstance.register(emailText, passwordText, this);
            } else {
                setError(emailText, passwordText);
            }
        });
    }

    private void setError(String email, String password) {
        String errorText = "";
        if (email.isEmpty())
            errorText += "Email field is empty\n";

        if (password.isEmpty())
            errorText += "Password field is empty\n";

        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(errorText)
                .setPositiveButton("Okay", (dialogInterface, i) -> {})
                .create()
                .show();
    }

    private void initViews() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
    }
}