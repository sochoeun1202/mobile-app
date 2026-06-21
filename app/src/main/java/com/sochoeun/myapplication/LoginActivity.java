package com.sochoeun.myapplication;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etUsername = findViewById(R.id.et_username);
        EditText etPassword = findViewById(R.id.et_password);
        LinearLayout btnLogin = findViewById(R.id.btn_login);
        ImageView btnTogglePassword = findViewById(R.id.btn_toggle_password);
        TextView tvSignUpLink = findViewById(R.id.tv_sign_up_link);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString();

                if (username.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.d(TAG, "Login Attempt - Username: " + username);
                Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                // Reset subscription status on new login for demonstration purposes
                getSharedPreferences("metabolic_prefs", MODE_PRIVATE)
                    .edit()
                    .putBoolean("is_subscribed", false)
                    .apply();

                // Navigate to MainActivity (Home/Dashboard)
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("USERNAME", username); // Pass the username to dashboard
                startActivity(intent);
                finish(); // Close Login screen so back button doesn't return here
            }
        });

        btnTogglePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // Hide password
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    btnTogglePassword.setImageResource(R.drawable.ic_visibility);
                    btnTogglePassword.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(LoginActivity.this, R.color.label_grey)));
                    btnTogglePassword.setContentDescription("Show Password");
                    isPasswordVisible = false;
                } else {
                    // Show password
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    btnTogglePassword.setImageResource(R.drawable.ic_visibility_off);
                    btnTogglePassword.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(LoginActivity.this, R.color.primary_green)));
                    btnTogglePassword.setContentDescription("Hide Password");
                    isPasswordVisible = true;
                }
                // Move cursor to the end of the text
                etPassword.setSelection(etPassword.getText().length());
            }
        });

        // Navigate to Signup screen
        tvSignUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
