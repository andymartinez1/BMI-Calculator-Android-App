package com.andymartinez1.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button calculateButton;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private EditText ageEditText;
    private EditText heightInFeetText;
    private EditText heightInInchesText;
    private EditText weightEditText;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViews();
        setupButtonClickListener();
    }

    private void findViews() {
        maleRadioButton = findViewById(R.id.radio_button_male);
        femaleRadioButton = findViewById(R.id.radio_button_female);
        ageEditText = findViewById(R.id.edit_text_age);
        heightInFeetText = findViewById(R.id.edit_text_feet);
        heightInInchesText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
        resultText = findViewById(R.id.text_view_result);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmiResult = calculateBMI();
                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18) {
                    displayResult(bmiResult);
                } else {
                    displayGuidance(bmiResult);
                }

            }
        });
    }

    private double calculateBMI() {
        String feetText = heightInFeetText.getText().toString();
        String inchesText = heightInInchesText.getText().toString();
        String weightText = weightEditText.getText().toString();

        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;
        double heightInMeters = totalInches * 0.0254;
        return weight / (heightInMeters * heightInMeters);
    }

    private void displayResult(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;

        if (bmi < 18.5) {
            fullResultString = bmiTextResult + " - You are underweight";
        } else if (bmi > 25) {
            fullResultString = bmiTextResult + " - You are overweight";
        } else {
            fullResultString = bmiTextResult + " - You are at a healthy weight!";
        }
        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;

        if (maleRadioButton.isChecked()) {
            fullResultString = bmiTextResult + " - You are under 18. Please consult with your doctor for the healthy range for boys.";
        } else if (femaleRadioButton.isChecked()) {
            fullResultString = bmiTextResult + " - You are under 18. Please consult with your doctor for the healthy range for girls.";
        } else {
            fullResultString = bmiTextResult + " - You are under 18. Please consult with your doctor.";
        }
        resultText.setText(fullResultString);
    }
}