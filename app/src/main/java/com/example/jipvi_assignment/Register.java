package com.example.jipvi_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Register extends AppCompatActivity {

    private RadioGroup radioGroup, radioGroup1;
    private RadioButton radioCm, radioFeetInches,radioKg, radioPound;
    private LinearLayout layoutCm, layoutFeetInches , layoutKg, layoutPound;
    private EditText editTextCm, editTextFeet, editTextInches , editTextKg, editTextPound;
    private Button buttonHeight , buttonWeight , button_all;
    private TextView tvSelectedDate;
    private Button btnSelectDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        radioGroup = findViewById(R.id.radioGroup);
        radioCm = findViewById(R.id.radioCm);
        radioFeetInches = findViewById(R.id.radioFeetInches);
        layoutCm = findViewById(R.id.layoutCm);
        layoutFeetInches = findViewById(R.id.layoutFeetInches);
        editTextCm = findViewById(R.id.editTextCm);
        editTextFeet = findViewById(R.id.editTextFeet);
        editTextInches = findViewById(R.id.editTextInches);
        buttonHeight = findViewById(R.id.buttonHeight);

        radioGroup1 = findViewById(R.id.radioGroup1);
        radioKg = findViewById(R.id.radioKg);
        radioPound = findViewById(R.id.radioPound);
        layoutKg = findViewById(R.id.layoutKg);
        layoutPound = findViewById(R.id.layoutPound);
        editTextKg = findViewById(R.id.editTextKg);
        editTextPound = findViewById(R.id.editTextPound);
        buttonWeight = findViewById(R.id.buttonWeight);

        button_all = findViewById(R.id.button_all);

        tvSelectedDate = findViewById(R.id.tvSelectedDate);
        btnSelectDate = findViewById(R.id.btnSelectDate);

        btnSelectDate.setOnClickListener(v -> showDatePickerDialog());

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioCm) {
                layoutCm.setVisibility(LinearLayout.VISIBLE);
                layoutFeetInches.setVisibility(LinearLayout.GONE);
            } else if (checkedId == R.id.radioFeetInches) {
                layoutCm.setVisibility(LinearLayout.GONE);
                layoutFeetInches.setVisibility(LinearLayout.VISIBLE);
            }
        });

        buttonHeight.setOnClickListener(view -> {
            if (radioCm.isChecked()) {
                String cmStr = editTextCm.getText().toString();
                if (TextUtils.isEmpty(cmStr)) {
                    Toast.makeText(this, "Please enter height in cm", Toast.LENGTH_SHORT).show();
                } else {
                    double cm = Double.parseDouble(cmStr);
                    Toast.makeText(this, "Height in cm: " + cm, Toast.LENGTH_SHORT).show();
                }
            } else if (radioFeetInches.isChecked()) {
                String feetStr = editTextFeet.getText().toString();
                String inchesStr = editTextInches.getText().toString();

                if (TextUtils.isEmpty(feetStr)) {
                    feetStr = "0";
                }
                if (TextUtils.isEmpty(inchesStr)) {
                    inchesStr = "0";
                }

                int feet = Integer.parseInt(feetStr);
                int inches = Integer.parseInt(inchesStr);

                if (inches >= 12) {
                    Toast.makeText(this, "Inches should be less than 12", Toast.LENGTH_SHORT).show();
                    return;
                }

                int totalInches = (feet * 12) + inches;
                double cm = totalInches * 2.54;
                Toast.makeText(this, "Height: " + feet + " ft " + inches + " in (" + cm + " cm)", Toast.LENGTH_SHORT).show();
            }
        });

        radioGroup1.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioKg) {
                layoutKg.setVisibility(LinearLayout.VISIBLE);
                layoutPound.setVisibility(LinearLayout.GONE);
            } else if (checkedId == R.id.radioPound) {
                layoutKg.setVisibility(LinearLayout.GONE);
                layoutPound.setVisibility(LinearLayout.VISIBLE);
            }
        });

        buttonWeight.setOnClickListener(view -> {
            if (radioKg.isChecked()) {
                String kgStr = editTextKg.getText().toString();
                if (TextUtils.isEmpty(kgStr)) {
                    Toast.makeText(this, "Please enter weight in kg", Toast.LENGTH_SHORT).show();
                } else {
                    double kg = Double.parseDouble(kgStr);
                    Toast.makeText(this, "Weight in kg: " + kg, Toast.LENGTH_SHORT).show();
                }
            } else if (radioPound.isChecked()) {
                String lbsStr = editTextPound.getText().toString();
                if (TextUtils.isEmpty(lbsStr)) {
                    Toast.makeText(this, "Please enter weight in lbs", Toast.LENGTH_SHORT).show();
                } else {
                    double lbs = Double.parseDouble(lbsStr);
                    double kg = lbs * 0.453592; // Convert lbs to kg
                    Toast.makeText(this, "Weight in lbs: " + lbs + " lbs (" + kg + " kg)", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Dashboard.class);
                startActivity(intent);
            }
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
            tvSelectedDate.setText(selectedDate);
        }, year, month, day);

        datePickerDialog.show();
    }
}