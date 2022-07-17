package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView requiredMonthPay;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        requiredMonthPay = findViewById(R.id.requiredMonthPay);

        Bundle arguments = getIntent().getExtras();

        double requiredMonthPayValue = arguments.getDouble("requiredMonthPay");

        requiredMonthPay.setText(Double.toString(requiredMonthPayValue));

    }
}