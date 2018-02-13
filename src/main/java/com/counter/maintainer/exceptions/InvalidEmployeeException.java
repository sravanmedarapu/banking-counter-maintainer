package com.counter.maintainer.exceptions;


public class InvalidEmployeeException extends RuntimeException{
    public InvalidEmployeeException(String errMSG) {
        super(errMSG);
    }
}
