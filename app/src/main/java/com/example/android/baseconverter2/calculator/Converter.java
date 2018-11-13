package com.example.android.baseconverter2.calculator;

public class Converter {
    /**
     * Method takes a String, that can only contain a number, written in numeral system (from 2 to 36),
     * and returns its decimal representation. In numeral systems with base greater than 10, it doesn't matter in which
     * case letters are written (e.g. "1fAec" = "1FAEC" = "1faec", etc).
     *
     * @param radixFrom
     * @return
     */
    public static String convertFrom(String number, int radixFrom) {
        boolean negative = false;
        if (number.contains("-")) {
            negative = true;
            number = number.replace("-", "");
        }
        String intPart = number.split("\\.")[0];
        DigitArray dgAr = new DigitArray(String.valueOf(intPart), radixFrom);
        int[] original = dgAr.getArray();
        int lastEl = original.length - 1;
        long dec = 0;
        for (int i = 0; i <= lastEl; i++) {
            dec += original[lastEl - i] * Math.pow(radixFrom, i);

        }

        String s = "";
        if (number.contains(".")) {
            String fractPart = number.split("\\.")[1];
            double sum = 0;
            for (int j = 1; j <= fractPart.length(); j++) {
                int digit = new Integer(Character.digit(fractPart.charAt(j - 1), radixFrom));
                sum += digit * Math.pow(radixFrom, -j);
            }
            s = "." + String.valueOf(sum).split("\\.")[1];
        }

        if (negative) {
            return "-" + dec + "" + s;
        } else {
            return dec + "" + s;
        }
    }

    /**
     * Method takes a string with a decimal number and returns its representation in radix given. In numeral systems with
     * base greater than 10, all letters in returned number are in upper case.
     *
     * @param number
     * @param radixTo
     * @return
     */
    public static String convertTo(String number, int radixTo) {
        boolean negative = false;
        int[] array;
        String str;
        if (number.contains("-")) {
            negative = true;
            number = number.replace("-", "");
        }

        int intPart = new Integer(number.split("\\.")[0]);
        if(intPart!=0) {
            int size = (int) (Math.log(intPart) / Math.log(radixTo)) + 1;

            array = new int[size];
            for (int i = size - 1; i >= 0; i--) {
                array[i] = intPart % radixTo;
                intPart /= radixTo;
            }
            str = "";
            for (int j : array) {
                if (j < 10) {
                    str += String.valueOf(j);
                } else {
                    str += (char) (j + 55);
                }
            }
        } else {
            str = "0";
        }
        String afterPoint = "";
        if (number.contains(".")) {
            double fractPart = new Double("0." + number.split("\\.")[1]);
            if (fractPart != 0) {
                int t = 0;
                array = new int[10];
                while (t < 10) {
                    fractPart *= radixTo;
                    array[t] = (int) fractPart;
                    fractPart -= (int) fractPart;
                    t++;
                }
                for (int j : array) {

                    if (j < 10) {
                        afterPoint += String.valueOf(j);
                    } else {
                        afterPoint += (char) (j + 55);
                    }

                }
                afterPoint = "." + afterPoint;
            }

        }
        if (negative) {
            return "-" + str + "" + afterPoint;
        } else {
            return str + "" + afterPoint;
        }
    }

    public static String convertFromTo(String number, int radixFrom, int radixTo) {
        String temp = Converter.convertFrom(number, radixFrom);
        temp = Converter.convertTo(temp, radixTo);
        return temp;
    }


}
