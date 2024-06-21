package com.example.jipvi_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Bmi extends AppCompatActivity {

    private RadioGroup radioGroupHeight, radioGroupWeight;
    private RadioButton radioCm, radioFeetInches, radioKg, radioPound;
    private LinearLayout layoutCm, layoutFeetInches, layoutKg, layoutPound;
    private EditText editTextCm, editTextFeet, editTextInches, editTextKg, editTextPound;
    private Button btnCalculate;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        radioGroupHeight = findViewById(R.id.radioGroup);
        radioGroupWeight = findViewById(R.id.radioGroup1);
        radioCm = findViewById(R.id.radioCm);
        radioFeetInches = findViewById(R.id.radioFeetInches);
        radioKg = findViewById(R.id.radioKg);
        radioPound = findViewById(R.id.radioPound);
        layoutCm = findViewById(R.id.layoutCm);
        layoutFeetInches = findViewById(R.id.layoutFeetInches);
        layoutKg = findViewById(R.id.layoutKg);
        layoutPound = findViewById(R.id.layoutPound);
        editTextCm = findViewById(R.id.editTextCm);
        editTextFeet = findViewById(R.id.editTextFeet);
        editTextInches = findViewById(R.id.editTextInches);
        editTextKg = findViewById(R.id.editTextKg);
        editTextPound = findViewById(R.id.editTextPound);
        btnCalculate = findViewById(R.id.btn_cal);
        result = findViewById(R.id.result);

        // Handle height radio group change
        radioGroupHeight.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioCm) {
                layoutCm.setVisibility(View.VISIBLE);
                layoutFeetInches.setVisibility(View.GONE);
            } else if (checkedId == R.id.radioFeetInches) {
                layoutCm.setVisibility(View.GONE);
                layoutFeetInches.setVisibility(View.VISIBLE);
            }
        });

        // Handle weight radio group change
        radioGroupWeight.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioKg) {
                layoutKg.setVisibility(View.VISIBLE);
                layoutPound.setVisibility(View.GONE);
            } else if (checkedId == R.id.radioPound) {
                layoutKg.setVisibility(View.GONE);
                layoutPound.setVisibility(View.VISIBLE);
            }
        });

        // Handle calculate button click
        btnCalculate.setOnClickListener(v -> calculateBmi());
    }

    private void calculateBmi() {
        float height = 0;
        float weight = 0;

        // Get height
        if (radioCm.isChecked()) {
            String heightCmStr = editTextCm.getText().toString();
            if (!heightCmStr.isEmpty()) {
                height = Float.parseFloat(heightCmStr) / 100;
            }
        } else if (radioFeetInches.isChecked()) {
            String heightFeetStr = editTextFeet.getText().toString();
            String heightInchesStr = editTextInches.getText().toString();
            if (!heightFeetStr.isEmpty() && !heightInchesStr.isEmpty()) {
                float feet = Float.parseFloat(heightFeetStr);
                float inches = Float.parseFloat(heightInchesStr);
                height = feet * 0.3048f + inches * 0.0254f;
            }
        }

        // Get weight
        if (radioKg.isChecked()) {
            String weightKgStr = editTextKg.getText().toString();
            if (!weightKgStr.isEmpty()) {
                weight = Float.parseFloat(weightKgStr);
            }
        } else if (radioPound.isChecked()) {
            String weightPoundStr = editTextPound.getText().toString();
            if (!weightPoundStr.isEmpty()) {
                weight = Float.parseFloat(weightPoundStr) * 0.453592f;
            }
        }

        // Calculate BMI
        if (height > 0 && weight > 0) {
            float bmi = weight / (height * height);
            result.setText(String.format("%.2f", bmi));
        } else {
            result.setText("Invalid input");
        }
    }
}