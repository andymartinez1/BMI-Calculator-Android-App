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

public class MainActivity extends AppCompatActivity {

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

        RadioButton maleRadioButton = findViewById(R.id.radio_button_male);
        RadioButton femaleRadioButton = findViewById(R.id.radio_button_female);

        EditText ageText = findViewById(R.id.edit_text_age);
        EditText heightInFeetText = findViewById(R.id.edit_text_feet);
        EditText heightInInchesText = findViewById(R.id.edit_text_inches);
        EditText weightText = findViewById(R.id.edit_text_weight);

        Button calculateButton = findViewById(R.id.button_calculate);

        TextView resultText = findViewById(R.id.text_view_result);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Calculating BMI", Toast.LENGTH_SHORT).show();
            }
        });

    }

}