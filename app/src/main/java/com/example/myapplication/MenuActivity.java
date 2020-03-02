package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private Button infoButton;
    private Button twelveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findComponent();
        bindOnClick();
    }

    private void findComponent() {
        infoButton = findViewById(R.id.infoButton);
        twelveButton = findViewById(R.id.twelveButton);
    }

    private void bindOnClick() {
        infoButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        twelveButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, BlackjackActivity.class);
            startActivity(intent);
        });
    }

}
