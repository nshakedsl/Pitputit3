package com.example.pitputitandroid;

import java.util.Calendar;
import java.util.UUID;

public class Utils {
    public static String uniqueIdGenerator() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String minuteStr = minute > 10 ? Integer.toString(minute) : "0" + minute;
        String hourStr = hour > 10 ? Integer.toString(hour) : "0" + hour;

        return hourStr+":"+minuteStr;
    }

}
