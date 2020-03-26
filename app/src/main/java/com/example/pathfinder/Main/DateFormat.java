package com.example.pathfinder.Main;

import java.text.SimpleDateFormat;

public class DateFormat {
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy.MM.dd");

    public static String getDateFormat(long time){
        return simpleDateFormat.format(time);
    }
}
