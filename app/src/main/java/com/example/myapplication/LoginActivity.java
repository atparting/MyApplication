package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText userNameText;
    private EditText passwordText;

    private Toast loginFailToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findComponent();
        loginButton.setOnClickListener(view -> {
            if (login()) {
                onLoginSuccess();
            } else {
                onLoginFail();
            }
        });
    }

    private void findComponent() {
        loginButton = findViewById(R.id.loginButton);
        userNameText = findViewById(R.id.userNameEditText);
        passwordText = findViewById(R.id.passwordEditText);
    }

    private boolean login() {
        return userNameText.getText().toString().equals("admin") &&
                passwordText.getText().toString().equals("111111");
    }

    private void onLoginSuccess() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void onLoginFail() {
        if (loginFailToast == null) {
            loginFailToast = Toast.makeText(this, null, Toast.LENGTH_SHORT);
            loginFailToast.show();
        } else {
            loginFailToast.show();
        }
    }
}
