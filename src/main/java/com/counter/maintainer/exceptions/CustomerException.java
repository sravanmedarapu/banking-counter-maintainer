package com.counter.maintainer.exceptions;

public class CustomerException extends RuntimeException{

    public CustomerException(String errMSG) {
        super(errMSG);
    }
}
