package com.qfedu.utils;

import java.text.DecimalFormat;

public class DateChangeUtils {

    public static String dateChange(int time) {

        int hour = time / 3600;

        int temp = time % 3600;

        int minute = temp / 60;

        int second = temp % 60;


        StringBuffer stringBuffer = new StringBuffer();

        DecimalFormat decimalFormat = new DecimalFormat("00");
        stringBuffer.append(decimalFormat.format(hour)+":");
        stringBuffer.append(decimalFormat.format(minute)+":");
        stringBuffer.append(decimalFormat.format(second));


        return stringBuffer.toString();
    }


    public static void main(String[] args) {
        String s = dateChange(7200);
        System.out.println(s);
    }
}
