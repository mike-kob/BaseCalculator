package com.example.android.baseconverter2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.baseconverter2.calculator.Converter;
import com.example.android.baseconverter2.calculator.Verifier;

public class ConverterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
    }

    public void onConvertAction(View view) {
        EditText num = findViewById(R.id.inputNumber);
        String numberStr = num.getText().toString();
        EditText rFrom = findViewById(R.id.inputRadixFrom);
        String radixFromStr = rFrom.getText().toString();
        EditText rTo = findViewById(R.id.inputRadixTo);
        String radixToStr = rTo.getText().toString();


        if (radixFromStr.isEmpty() || radixToStr.isEmpty()) {
            Toast.makeText(getBaseContext(), "Enter radix, please", Toast.LENGTH_SHORT).show();
            return;
        }

        int inputRadixFrom = Integer.parseInt(radixFromStr);
        int inputRadixTo = Integer.parseInt(radixToStr);
        if (inputRadixFrom > 36 || inputRadixFrom < 2 || inputRadixTo > 36 || inputRadixTo < 2) {
            Toast.makeText(getBaseContext(), "Radix must be from 2 to 36", Toast.LENGTH_SHORT).show();
            return;
        }

        if (numberStr.isEmpty()) {
            Toast.makeText(getBaseContext(), "Enter the number, please", Toast.LENGTH_SHORT).show();
            return;
        }

        Verifier ver = new Verifier(numberStr, inputRadixFrom);
        if (!ver.isCorrect()) {
            Toast.makeText(getBaseContext(), "The number has illegal digits", Toast.LENGTH_SHORT).show();
            return;
        }


        String answer = Converter.convertFromTo(numberStr, inputRadixFrom, inputRadixTo);
        TextView ans = findViewById(R.id.answerConvert);
        ans.setText(answer);
    }

    public void changeActivityToCalculator(View view) {
        finish();
    }

}
