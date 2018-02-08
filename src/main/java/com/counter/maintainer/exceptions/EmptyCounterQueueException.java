package com.counter.maintainer.exceptions;

public class EmptyCounterQueueException extends Exception {

    public EmptyCounterQueueException() {
        super("CounterDetails Queue is empty");
    }

    public EmptyCounterQueueException(String msg) {
        super("CounterDetails Queue is empty:"+msg);
    }

}
