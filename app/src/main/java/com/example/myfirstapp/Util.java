package com.example.myfirstapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String dateToString(Date date){

        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
        String strDate = dateFormat.format(date);

        return strDate;
    }
}
