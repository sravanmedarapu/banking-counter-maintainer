package com.counter.maintainer.exceptions;

public class InsufficientPrivilegesException extends RuntimeException {
    public InsufficientPrivilegesException(String errMsg) {
        super(errMsg);
    }
}
