package com.alex.study.springsecurity.domain.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class TimeUtil {

    public static final long SEVEN_DAYS_MS = 7 * 24 * 60 * 60 * 1000;

    /**
     * 将日期时间精确到日
     */
    public static Date toDayFormat(Date date) {
        DateFormat dateFormat = DateFormat.getDateInstance();
        String dateString = dateFormat.format(date);

        Date convertedDate = null;
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertedDate;
    }
}
