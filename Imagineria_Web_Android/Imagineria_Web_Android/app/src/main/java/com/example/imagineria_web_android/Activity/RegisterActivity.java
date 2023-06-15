package com.example.imagineria_web_android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.imagineria_web_android.API.AuthAPI;
import com.example.imagineria_web_android.API.UserApi;
import com.example.imagineria_web_android.Model.Auth.RegisterRequest;
import com.example.imagineria_web_android.Model.Auth.RegisterResponse;
import com.example.imagineria_web_android.Model.Auth.User;
import com.example.imagineria_web_android.R;
import com.example.imagineria_web_android.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

        private EditText usernameEditText;
        private EditText emailEditText;
        private EditText passwordEditText;
        private EditText verifyPasswordEditText;

        private EditText nameEditTetx;
        private Button registerButton, cancelarButton;
        private ProgressBar progressBar;
        private AuthAPI userApi;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            usernameEditText = findViewById(R.id.username);
            emailEditText = findViewById(R.id.email);
            passwordEditText = findViewById(R.id.password);
            verifyPasswordEditText = findViewById(R.id.verifyPassword);
            registerButton = findViewById(R.id.btn_register);
            progressBar = findViewById(R.id.progressBar);
            nameEditTetx = findViewById(R.id.name);
            cancelarButton = findViewById(R.id.cancelarNow);

            userApi = RetrofitInstance.getRetrofitInstance(getApplicationContext()).create(AuthAPI.class);

            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = usernameEditText.getText().toString().trim();
                    String email = emailEditText.getText().toString().trim();
                    String password = passwordEditText.getText().toString().trim();
                    String verifyPassword = verifyPasswordEditText.getText().toString().trim();
                    String name =  nameEditTetx.getText().toString().trim();

                    if (validateInputs(username, email, password, verifyPassword)) {
                        RegisterRequest registerRequest = new RegisterRequest(username, email, password, name, verifyPassword);
                        registerUser(registerRequest);
                    }
                }
            });

            cancelarButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        }

        private boolean validateInputs(String username, String email, String password, String verifyPassword) {
            if (TextUtils.isEmpty(username)) {
                usernameEditText.setError("Username es obligatorio");
                return false;
            }

            if (TextUtils.isEmpty(email)) {
                emailEditText.setError("Email es obligatorio");
                return false;
            }

            if (TextUtils.isEmpty(password)) {
                passwordEditText.setError("Password es obligatorio");
                return false;
            }

            if (!password.equals(verifyPassword)) {
                verifyPasswordEditText.setError("");
                return false;
            }

            return true;
        }

        private void registerUser(RegisterRequest registerRequest) {
            progressBar.setVisibility(View.VISIBLE);

            userApi.registerUser(registerRequest).enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    progressBar.setVisibility(View.GONE);

                    if (response.isSuccessful()) {
                        RegisterResponse registerResponse = response.body();

                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
