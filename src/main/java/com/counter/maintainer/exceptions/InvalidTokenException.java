package com.counter.maintainer.exceptions;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(String errMSg) {
        super(errMSg);
    }

    public InvalidTokenException(String errMsg, Exception e) {
        super(errMsg, e);
    }
}
