package com.example.android.baseconverter2;

import android.content.Context;
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
        spinner = (Spinner) findViewById(R.id.spinner);
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

    public void calc(View view) {

        //This block hides keyboard after clicking Convert button
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        //--------------------------------------------------------

        EditText p1 = (EditText) findViewById(R.id.editText1);
        EditText p2 = (EditText) findViewById(R.id.editText2);
        int radixInput =0;
        EditText r1 = (EditText) findViewById(R.id.radixInput);

        if (r1.getText().toString().equals("")) {

            Toast.makeText(getBaseContext(),"Enter radix, please",Toast.LENGTH_SHORT).show();
            return;
        } else{
            radixInput = new Integer(r1.getText().toString());
            if(radixInput>36||radixInput<2){
                Toast.makeText(getBaseContext(),"Radix must be from 2 to 36",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (p1.getText().toString().equals("") || p2.getText().toString().equals("")) {
            Toast.makeText(getBaseContext(),"You must enter both parameters",Toast.LENGTH_SHORT).show();
            return;
        }
        String param1 = p1.getText().toString();
        String param2 = p2.getText().toString();


        Verifier ver = new Verifier(param1,radixInput);
        if(!ver.isCorrect()){
            Toast.makeText(getBaseContext(),"A parameter has illegal digits",Toast.LENGTH_SHORT).show();
            return;
        }

        ver = new Verifier(param2, radixInput);
        if(!ver.isCorrect()){
            Toast.makeText(getBaseContext(),"A parameter has illegal digits",Toast.LENGTH_SHORT).show();
            return;
        }

        String result = Calculator.getResult(param1,param2,radixInput,currentOperation);

        TextView t1 = (TextView)findViewById(R.id.answerConvert);
        t1.setText(result);

        TextView t2 = (TextView)findViewById((R.id.answer_in_decimal));
        if(result.equals("Error: cannot divide by zero!")){
            t2.setText(result);
        } else {
            t2.setText(Converter.convertFrom(result,radixInput));
        }

    }

    public void changeActivityToConverter(View view){
       /* Intent changeToConverter = new Intent(getApplicationContext(),ConverterActivity.class);
        startActivity(changeToConverter);*/
       setContentView(R.layout.activity_converter);
    }

    public void convert(View view){

        //This block hides keyboard after clicking Convert button
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        //--------------------------------------------------------

        EditText num = (EditText)findViewById(R.id.inputNumber);
        EditText rFrom = (EditText)findViewById(R.id.inputRadixFrom);
        EditText rTo = (EditText)findViewById(R.id.inputRadixTo);


        int inputRadixFrom;
        int inputRadixTo;

        if (rFrom.getText().toString().equals("")||rTo.getText().toString().equals("")) {

            Toast.makeText(getBaseContext(),"Enter radix, please",Toast.LENGTH_SHORT).show();
            return;
        } else{
            inputRadixFrom = new Integer(rFrom.getText().toString());
            inputRadixTo = new Integer(rTo.getText().toString());
            if(inputRadixFrom>36||inputRadixFrom<2||inputRadixTo>36||inputRadixTo<2){
                Toast.makeText(getBaseContext(),"Radix must be from 2 to 36",Toast.LENGTH_SHORT).show();
                return;
            }

        }

        if(num.getText().toString().isEmpty()){
            Toast.makeText(getBaseContext(),"Enter number, please",Toast.LENGTH_SHORT).show();
            return;
        }
        Verifier ver = new Verifier(num.getText().toString(),inputRadixFrom);
        if(!ver.isCorrect()){
            Toast.makeText(getBaseContext(),"The number has illegal digits",Toast.LENGTH_SHORT).show();
            return;
        }






        String answer = Converter.convertFromTo(num.getText().toString(), inputRadixFrom, inputRadixTo);

        TextView ans = (TextView)findViewById(R.id.answerConvert);
        ans.setText(answer);

    }
    public void changeActivityToCalculator(View view){
        /*Intent changeToCalculator = new Intent(getApplicationContext(),CalculatorActivity.class);
        startActivity(changeToCalculator);*/
        setContentView(R.layout.activity_calculator);
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.operations, R.layout.spinner_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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

}
