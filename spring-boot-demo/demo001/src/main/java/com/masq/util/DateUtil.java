package com.masq.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String DEFAULT_FORM = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORM_WITHOUT_LINE = "yyyyMMdd";
    public static final String DATE_FORM = "yyyy-MM-dd";

    public static String dateToString(Date date, String dateForm) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateForm);
        return sdf.format(date);
    }

    public static String currentDateWithDefault() {
        return dateToString(new Date(), DEFAULT_FORM);
    }

    public static String currentDateWithCustom(String form) {
        return dateToString(new Date(), form);
    }
}
