package com.example.interviewproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private String TAG = LoginActivity.class.getSimpleName();

    private EditText usernameET;
    private EditText passwordET;
    private Button loginButton;
    ApiClient apiClient;
    private static String auth_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Builds HTTP Client for API Calls
        apiClient = RetrofitClient.buildHTTPClient();


        usernameET = findViewById(R.id.usernameEditText);
        passwordET = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }

    private void doLogin() {
        String username = usernameET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();

        if (username.isEmpty()) {
            usernameET.setError("User Name is required.");
            usernameET.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            usernameET.setError("Enter a valid user name.");
            usernameET.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            passwordET.setError("Password is required.");
            passwordET.requestFocus();
            return;
        }

        Post post = new Post(username, password);
        Call<LoginResponse> call = apiClient.userLogin(post);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                auth_token = response.body().getToken();

                Intent intent = new Intent(LoginActivity.this, InfoActivity.class);
                intent.putExtra("auth_token", auth_token);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}