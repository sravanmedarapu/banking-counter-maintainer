package com.counter.maintainer.exceptions;

public class TokenException extends RuntimeException{
    public TokenException(String errMSG, Exception e) {
        super(errMSG, e);
    }

    public TokenException(String errMSG) {
        super(errMSG);
    }

}
