package com.zeffee.converter;

import org.springframework.format.Formatter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by zeffee on 2017/6/7.
 */
public class ThemeDateTimeFormatter implements Formatter<Timestamp> {
    @Override
    public Timestamp parse(String s, Locale locale) throws ParseException {
        System.out.println(123);
        return Timestamp.valueOf(s);
    }

    @Override
    public String print(Timestamp timestamp, Locale locale) {
        return null;
    }
}
