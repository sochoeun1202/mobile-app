package com.sochoeun.myapplication;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText etUsername = findViewById(R.id.et_username);
        EditText etPhone = findViewById(R.id.et_phone);
        EditText etPassword = findViewById(R.id.et_password);
        EditText etConfirmPassword = findViewById(R.id.et_confirm_password);
        CheckBox cbTerms = findViewById(R.id.cb_terms);
        LinearLayout btnSignup = findViewById(R.id.btn_signup);
        ImageView btnTogglePassword = findViewById(R.id.btn_toggle_password);
        ImageView btnToggleConfirmPassword = findViewById(R.id.btn_toggle_confirm_password);
        TextView tvSignInLink = findViewById(R.id.tv_sign_in_link);

        // Underline "Term of Privacy Policy" text programmatically or set it in string
        TextView tvTerms = findViewById(R.id.tv_terms);
        tvTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbTerms.setChecked(!cbTerms.isChecked());
            }
        });

        // Toggle Password Visibility
        btnTogglePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    btnTogglePassword.setImageResource(R.drawable.ic_visibility);
                    btnTogglePassword.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(SignupActivity.this, R.color.label_grey)));
                    btnTogglePassword.setContentDescription("Show Password");
                    isPasswordVisible = false;
                } else {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    btnTogglePassword.setImageResource(R.drawable.ic_visibility_off);
                    btnTogglePassword.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(SignupActivity.this, R.color.primary_green)));
                    btnTogglePassword.setContentDescription("Hide Password");
                    isPasswordVisible = true;
                }
                etPassword.setSelection(etPassword.getText().length());
            }
        });

        // Toggle Confirm Password Visibility
        btnToggleConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConfirmPasswordVisible) {
                    etConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    btnToggleConfirmPassword.setImageResource(R.drawable.ic_visibility);
                    btnToggleConfirmPassword.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(SignupActivity.this, R.color.label_grey)));
                    btnToggleConfirmPassword.setContentDescription("Show Confirm Password");
                    isConfirmPasswordVisible = false;
                } else {
                    etConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    btnToggleConfirmPassword.setImageResource(R.drawable.ic_visibility_off);
                    btnToggleConfirmPassword.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(SignupActivity.this, R.color.primary_green)));
                    btnToggleConfirmPassword.setContentDescription("Hide Confirm Password");
                    isConfirmPasswordVisible = true;
                }
                etConfirmPassword.setSelection(etConfirmPassword.getText().length());
            }
        });

        // Navigation back to Sign In
        tvSignInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close this Activity and return to LoginActivity
            }
        });

        // Sign Up Action
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                if (username.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please enter phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!cbTerms.isChecked()) {
                    Toast.makeText(SignupActivity.this, "Please agree to the Term of Privacy Policy", Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.d(TAG, "Registration Success: " + username + ", Phone: " + phone);
                Toast.makeText(SignupActivity.this, "Registration Success!", Toast.LENGTH_LONG).show();
                finish(); // Navigate back to Login
            }
        });
    }
}
