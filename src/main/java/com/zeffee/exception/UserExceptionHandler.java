package com.zeffee.exception;

import com.zeffee.lib.Common;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by zeffee on 2017/6/20.
 */
@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(InvalidStatusException.class)
    @ResponseBody
    public Map<String, Object> invalidUserStatus(InvalidStatusException e) {
        return Common.getResponseMap(500, e.getMessage());
    }

    // request body & 400 bad request
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Map<String, Object> invalidUserStatus(HttpMessageNotReadableException e) {
        return Common.getResponseMap(500, e.getMessage().split(",", 2)[0]);
    }
}
