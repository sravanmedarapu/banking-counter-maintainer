package com.counter.maintainer.exceptions;


public class InvalidCustomerException extends RuntimeException {

    public InvalidCustomerException(String errMSG) {
        super(errMSG);
    }
}
