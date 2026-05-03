package com.sochoeun.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout tilTemperature;
    private TextInputEditText etTemperature;
    private MaterialButtonToggleGroup toggleGroup;
    private TextView tvResultValue;
    private TextView tvResultUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Initialize Views
        tilTemperature = findViewById(R.id.tilTemperature);
        etTemperature = findViewById(R.id.etTemperature);
        toggleGroup = findViewById(R.id.toggleGroup);
        tvResultValue = findViewById(R.id.tvResultValue);
        tvResultUnit = findViewById(R.id.tvResultUnit);
        MaterialButton btnConvert = findViewById(R.id.btnConvert);

        // 2. Set up Convert Button Click Listener
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTemperature();
            }
        });

        // Update unit label immediately when toggle changes
        toggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked) {
                    if (checkedId == R.id.btnCtoF) {
                        tvResultUnit.setText("°F");
                    } else {
                        tvResultUnit.setText("°C");
                    }
                }
            }
        });
    }

    private void convertTemperature() {
        String input = etTemperature.getText().toString().trim();

        if (input.isEmpty()) {
            tilTemperature.setError(getString(R.string.error_empty));
            return;
        }

        tilTemperature.setError(null);

        try {
            double value = Double.parseDouble(input);
            double result;
            int checkedId = toggleGroup.getCheckedButtonId();

            if (checkedId == R.id.btnCtoF) {
                // Celsius to Fahrenheit: F = C * 9/5 + 32
                result = (value * 9 / 5) + 32;
            } else {
                // Fahrenheit to Celsius: C = (F - 32) * 5/9
                result = (value - 32) * 5 / 9;
            }

            tvResultValue.setText(String.format(Locale.getDefault(), "%.2f", result));

        } catch (NumberFormatException e) {
            tilTemperature.setError("Invalid number");
        }
    }
}
