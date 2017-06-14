package com.zeffee.converter;

import com.zeffee.entity.Theme;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;

/**
 * Created by zeffee on 2017/6/7.
 */
public class ThemeDateTimeConverter implements Converter<Object,Timestamp> {

    @Override
    public Timestamp convert(Object datetime) {
        System.out.println(datetime.getClass()+"gg");

        return Timestamp.valueOf("2011-05-09 11:49:45");
    }
}
