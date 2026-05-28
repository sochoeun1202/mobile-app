package com.sochoeun.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        if (intent.hasExtra("SELECTED_PROVINCE")) {
            Province province = (Province) intent.getSerializableExtra("SELECTED_PROVINCE");

            ImageView imageView = findViewById(R.id.detailImage);
            TextView nameEnView = findViewById(R.id.detailNameEn);
            TextView nameKhView = findViewById(R.id.detailNameKh);

            nameEnView.setText(province.getNameEn());
            nameKhView.setText(province.getNameKh());

            // Handle image
            String imageName = province.getImageName().replace(".png", "");
            int resId = getResources().getIdentifier(imageName, "drawable", getPackageName());
            
            if (resId != 0) {
                imageView.setImageResource(resId);
            } else {
                imageView.setImageResource(R.drawable.ic_launcher_foreground);
            }
        }

        findViewById(R.id.fabBack).setOnClickListener(v -> finish());
    }

}