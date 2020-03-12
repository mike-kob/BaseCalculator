package com.example.android.baseconverter2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.baseconverter2.calculator.Calculator;
import com.example.android.baseconverter2.calculator.Converter;
import com.example.android.baseconverter2.calculator.Verifier;

public class CalculatorActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter adapter;
    byte currentOperation = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        initializeSpinner();
    }

    private void initializeSpinner() {
        spinner = findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.operations, R.layout.spinner_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentOperation = (byte) i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onCalcAction(View view) {
        hideKeyboard();

        EditText p1 = findViewById(R.id.editText1);
        EditText p2 = findViewById(R.id.editText2);

        int radixInput = 0;
        EditText r1 = findViewById(R.id.radixInput);

        if (r1.getText().toString().equals("")) {
            Toast.makeText(getBaseContext(), "Enter radix, please", Toast.LENGTH_SHORT).show();
            return;
        }

        String numberStr = r1.getText().toString();
        radixInput = Integer.parseInt(numberStr);

        if (radixInput > 36 || radixInput < 2) {
            Toast.makeText(getBaseContext(), "Radix must be from 2 to 36", Toast.LENGTH_SHORT).show();
            return;
        }

        String param1 = p1.getText().toString();
        String param2 = p2.getText().toString();
        if (param1.isEmpty() || param2.isEmpty()) {
            Toast.makeText(getBaseContext(), "You must enter both parameters", Toast.LENGTH_SHORT).show();
            return;
        }

        Verifier ver = new Verifier(param1, radixInput);
        if (!ver.isCorrect()) {
            Toast.makeText(getBaseContext(), "A parameter has illegal digits", Toast.LENGTH_SHORT).show();
            return;
        }

        ver = new Verifier(param2, radixInput);
        if (!ver.isCorrect()) {
            Toast.makeText(getBaseContext(), "A parameter has illegal digits", Toast.LENGTH_SHORT).show();
            return;
        }

        String result = Calculator.getResult(param1, param2, radixInput, currentOperation);

        TextView t1 = findViewById(R.id.answerConvert);
        t1.setText(result);

        TextView t2 = findViewById((R.id.answer_in_decimal));
        if (result.equals("Error: cannot divide by zero!")) {
            t2.setText(result);
        } else {
            t2.setText(Converter.convertFrom(result, radixInput));
        }

    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void changeActivityToConverter(View view) {
        Intent changeToConverter = new Intent(getApplicationContext(), ConverterActivity.class);
        startActivity(changeToConverter);
    }
}
