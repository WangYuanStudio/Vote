package com.zeffee.exception;

/**
 * Created by Zeffee on 2017/7/16.
 */
public class ServerException extends RuntimeException {
    public ServerException() {
    }

    public ServerException(String message) {
        super(message);
    }
}
