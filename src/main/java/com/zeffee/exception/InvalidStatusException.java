package com.zeffee.exception;

/**
 * Created by zeffee on 2017/6/20.
 */
public class InvalidStatusException extends RuntimeException {
    public InvalidStatusException() {
    }

    public InvalidStatusException(String message) {
        super(message);
    }
}
