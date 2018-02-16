package com.counter.maintainer.exceptions;


public class BranchNotExistsException extends RuntimeException{
    public BranchNotExistsException(String errMSG) {
        super(errMSG);
    }

    public BranchNotExistsException(String errMSG, Exception e) {
        super(errMSG, e);
    }
}
