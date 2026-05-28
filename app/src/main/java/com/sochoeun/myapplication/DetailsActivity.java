package com.sochoeun.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvFirstName = findViewById(R.id.tvFirstName);
        TextView tvLastName = findViewById(R.id.tvLastName);
        TextView tvPhone = findViewById(R.id.tvPhone);
        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvAddress = findViewById(R.id.tvAddress);
        Button btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("person")) {
            Person p = intent.getParcelableExtra("person");
            if (p != null) {
                tvFirstName.setText(p.getFirstname());
                tvLastName.setText(p.getLastname());
                tvPhone.setText(p.getPhone());
                tvEmail.setText(p.getEmail());

                String fullAddress = String.format("House %s, St %s, %s, %s, %s",
                        p.getHouse(), p.getStreet(), p.getSangKat(), p.getKhan(), p.getCity());
                tvAddress.setText(fullAddress);
            }
        }

        btnBack.setOnClickListener(v -> finish());
    }
}
