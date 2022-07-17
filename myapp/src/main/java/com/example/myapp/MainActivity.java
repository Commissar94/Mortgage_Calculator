package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText sumOfMortgage;
    EditText yearsOfMortgage;
    EditText percentOfMortgage;


    EditText etSelectDate;
    DatePickerDialog datePickerDialog;

    Button acceptData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumOfMortgage = findViewById(R.id.textInputEditSum);
        yearsOfMortgage = findViewById(R.id.textInputEditYearsOfMortgage);
        percentOfMortgage = findViewById(R.id.textInputEditPercentOfMortgage);

        etSelectDate = findViewById(R.id.textInputEditDate);

        acceptData = findViewById(R.id.buttonAcceptData);

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        etSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month += 1;
                                String date = day + "/" + month + "/" + year;
                                etSelectDate.setText(date);


                            }
                        }, year, month, day);
                datePickerDialog.show();

            }
        });

        createStartDateOfMortgageField();

        acceptData.setOnClickListener(this);
    }


    private void createStartDateOfMortgageField() {
        Calendar calendar = Calendar.getInstance();
        final int yearOfMortgageStart = calendar.get(Calendar.YEAR);
        final int monthOfMortgageStart = calendar.get(Calendar.MONTH);
        final int dayOfMortgageStart = calendar.get(Calendar.DAY_OF_MONTH);


        etSelectDate.setOnClickListener(view -> {

            DatePickerDialog.OnDateSetListener onDateSetListener = (datePicker, year, month, day) -> {
                month += 1;
                String date = day + "/" + month + "/" + year;
                etSelectDate.setText(date);
            };

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    MainActivity.this,
                    onDateSetListener,
                    yearOfMortgageStart,
                    monthOfMortgageStart,
                    dayOfMortgageStart);

            datePickerDialog.show();
        });
    }

    private void openSecondActivity(Intent intent) {
        startActivity(intent);
    }

    private Intent collectData(double requiredMonthPay) {

        Intent intent = new Intent(this, SecondActivity.class);

        intent.putExtra("requiredMonthPay", requiredMonthPay);

        return intent;
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onClick(View view) {
        float sum = 0;
        float percent = 0;
        float monthPercent = 0;
        int years = 0;
        int months = 0;

        float res = 0;

        if (TextUtils.isEmpty(sumOfMortgage.getText().toString())
                || TextUtils.isEmpty(percentOfMortgage.getText().toString())
                || TextUtils.isEmpty(etSelectDate.getText().toString())) {
            return;
        }

        sum = Float.parseFloat(sumOfMortgage.getText().toString());
        percent = Float.parseFloat(percentOfMortgage.getText().toString());
        monthPercent = percent / 12 / 100;
        years = Integer.parseInt(yearsOfMortgage.getText().toString());
        months = years * 12;
        double kef = (monthPercent * Math.pow((1 + monthPercent), months)) / (Math.pow((1 + monthPercent), months) - 1);

        Intent intent = collectData(sum*kef);
        openSecondActivity(intent);
    }
}