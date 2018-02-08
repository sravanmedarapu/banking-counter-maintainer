package com.counter.maintainer.exceptions;


public class CountersNotAvailableException extends RuntimeException {
    public CountersNotAvailableException() {
        super("Counters not available");
    }
}
