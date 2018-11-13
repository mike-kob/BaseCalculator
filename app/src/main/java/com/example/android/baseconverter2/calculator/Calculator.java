package com.example.android.baseconverter2.calculator;

public class Calculator {
    public static String getResult(String arg1, String arg2, int radix, byte operation) {
        double a, b;

        if(radix==10){
            a = new Double(arg1);
            b = new Double(arg2);
            if (b == 0 && operation == 3) {
                return "Error: cannot divide by zero!";
            }
        } else {
            String arg1Dec = Converter.convertFrom(arg1, radix);
            String arg2Dec = Converter.convertFrom(arg2, radix);
            a = new Double(arg1Dec);
            b = new Double(arg2Dec);
            if (b == 0 && operation == 3) {
                return "Error: cannot divide by zero!";
            }
        }
        double answerDec = 0;
        switch (operation) {
            case 0:
                answerDec = a + b;
                break;
            case 1:
                answerDec = a - b;
                break;
            case 2:
                answerDec = a * b;
                break;
            case 3:
                answerDec = a / b;
                break;
        }

        String answer = Converter.convertTo(answerDec+"", radix);
        return answer;
    }
}
