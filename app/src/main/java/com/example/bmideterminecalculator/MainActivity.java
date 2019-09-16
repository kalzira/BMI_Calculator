package com.example.bmideterminecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private EditText weight, height;
    private Button calculateButton;
    private TextView outputTextView;

    private float Output=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weight = findViewById(R.id.weightEditText);
        height = findViewById(R.id.heightEditText);
        calculateButton = findViewById(R.id.calculateButton);
        outputTextView = findViewById(R.id.output);
        Spinner spinner = findViewById(R.id.ageSpinner);
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 18; i <= 70; i++) {
            arrayList.add(i);
        }
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>
                (this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        final Integer selectedAge = (Integer) spinner.getSelectedItem();
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(weight.getText().toString()) || TextUtils.isEmpty(height.getText().toString())){
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.emptyField),
                            Toast.LENGTH_SHORT).show();
                } else {
                    float weightNum = Float.parseFloat(weight.getText().toString());
                    float heightNum = Float.parseFloat(height.getText().toString());
                    Output = weightNum / ((heightNum / 100) * (heightNum / 100));

                    if ((selectedAge <= 25 && Output < 17) || ((selectedAge >= 25 && selectedAge <= 46) && Output < 18) || (selectedAge > 46 && Output < 18.5)) {
                        outputTextView.setText(getResources().getString(R.string.BmiText) + String.format("%.2f", Output) + "\n" + getResources().getString(R.string.Underweight));
                    }
                    if ((selectedAge <= 25 && (Output >= 17.5 && Output <= 19.5)) || ((selectedAge >= 25 && selectedAge <= 46) && (Output >= 18 && Output <= 20))
                            || ((selectedAge > 46) && (Output >= 18.5 && Output <= 20.5))) {
                        outputTextView.setText(getResources().getString(R.string.BmiText) + String.format("%.2f", Output) + "\n" + getResources().getString(R.string.littleUnderweight));
                    }
                    if (((selectedAge <= 25 && (Output >= 19.5 && Output <= 22.9)) || ((selectedAge >= 25 && selectedAge <= 46) && (Output >= 20 && Output <= 25.9))
                            || (selectedAge > 46 && (Output >= 20.5 && Output <= 26.4)))) {
                        outputTextView.setText(getResources().getString(R.string.BmiText) + String.format("%.2f", Output) + "\n" + getResources().getString(R.string.normal));
                    }
                    if ((selectedAge <= 25 && Output > 23) || ((selectedAge >= 25 && selectedAge <= 46) && Output > 26) || (selectedAge > 46 && Output > 26.4)) {
                        outputTextView.setText(getResources().getString(R.string.BmiText) + String.format("%.2f", Output) + "\n" + getResources().getString(R.string.overweight));
                    }
                }
            }
        });
    }
}
