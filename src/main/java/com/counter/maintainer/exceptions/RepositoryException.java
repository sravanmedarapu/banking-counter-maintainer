package com.counter.maintainer.exceptions;

public class RepositoryException extends Exception{
    public RepositoryException(String errMSG, Exception e) {
        super(errMSG, e);
    }
}
