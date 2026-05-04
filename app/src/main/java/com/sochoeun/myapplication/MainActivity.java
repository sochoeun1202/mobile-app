package com.sochoeun.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Initialize Views
        EditText etCelsius = findViewById(R.id.etCelsius);
        EditText etResult  = findViewById(R.id.etResult);
        Button btnConvert = findViewById(R.id.btnConvert);

        // 2. Set up Convert Button Click Listener
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTemperature(etCelsius,etResult);
            }
        });

    }

    private void convertTemperature(EditText etCelsius, EditText etResult) {
        String input = etCelsius.getText().toString().trim();

        if (input.isEmpty()) {
            etCelsius.setError(getString(R.string.error_empty));
            return;
        }

        etCelsius.setError(null);

        try {
            double value = Double.parseDouble(input);
            double result;

            // Celsius to Fahrenheit: F = C * 9/5 + 32
            result = (value * 9 / 5) + 32;

            etResult.setText(String.format(Locale.getDefault(), "%.2f", result));

        } catch (NumberFormatException e) {
            etCelsius.setError("Invalid number");
        }
    }
}
