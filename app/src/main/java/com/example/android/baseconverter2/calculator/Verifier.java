package com.example.android.baseconverter2.calculator;

public class Verifier {
    private int radix;
    private String number;
    private boolean isCorrect = true;

    public Verifier(String number, int radix){
        this.number = number;
        this.radix = radix;
        check();
    }

    private void check(){
        for(int i = 0; i<number.length();i++){
            if(Character.digit(number.charAt(i),radix)<0&&number.charAt(i)!='.'){
                isCorrect = false;
            }
        }
    }

    public boolean isCorrect(){
        return isCorrect;
    }

}
