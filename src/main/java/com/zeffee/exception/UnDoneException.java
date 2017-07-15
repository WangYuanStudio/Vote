package com.zeffee.exception;

/**
 * Created by zeffee on 2017/6/20.
 */
public class UnDoneException extends RuntimeException {
    public UnDoneException() {
    }

    public UnDoneException(String message) {
        super(message);
    }
}
