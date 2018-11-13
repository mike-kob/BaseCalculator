package com.example.android.baseconverter2.calculator;

public class DigitArray {
    private int[] array;
    String number;
    int radix;

    public DigitArray(String num, int radix){
        number = num;
        this.radix = radix;
    }

    public int[] getArray() {
        array = new int[number.length()];
        for(int i =0;i<number.length();i++){
            array[i] = Character.digit(number.charAt(i), radix);
        }
        return array;
    }


}
