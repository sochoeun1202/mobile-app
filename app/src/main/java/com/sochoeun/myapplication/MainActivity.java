package com.sochoeun.myapplication;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.content.res.ColorStateList;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etUsername = findViewById(R.id.et_username);
        EditText etPassword = findViewById(R.id.et_password);
        LinearLayout btnLogin = findViewById(R.id.btn_login);
        ImageView btnTogglePassword = findViewById(R.id.btn_toggle_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                Log.d(TAG, "Username: " + username + ", Password: " + password);
            }
        });

        btnTogglePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // Hide password
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    btnTogglePassword.setImageResource(R.drawable.ic_visibility);
                    btnTogglePassword.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.label_grey)));
                    btnTogglePassword.setContentDescription("Show Password");
                    isPasswordVisible = false;
                } else {
                    // Show password
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    btnTogglePassword.setImageResource(R.drawable.ic_visibility_off);
                    btnTogglePassword.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.primary_green)));
                    btnTogglePassword.setContentDescription("Hide Password");
                    isPasswordVisible = true;
                }
                // Move cursor to the end of the text
                etPassword.setSelection(etPassword.getText().length());
            }
        });
    }
}
