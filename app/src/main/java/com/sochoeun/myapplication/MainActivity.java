package com.sochoeun.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvUsernameDisplay = findViewById(R.id.tv_username_display);
        LinearLayout btnLogout = findViewById(R.id.btn_logout);

        // Retrieve and display username passed from LoginActivity
        String username = getIntent().getStringExtra("USERNAME");
        if (username != null && !username.trim().isEmpty()) {
            tvUsernameDisplay.setText(username.toUpperCase());
        } else {
            tvUsernameDisplay.setText("CHAMPION");
        }

        // Handle Logout
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to LoginActivity
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Close MainActivity so back button doesn't reopen it
            }
        });
    }
}
