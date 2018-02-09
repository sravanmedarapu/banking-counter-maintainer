package com.counter.maintainer.service;

import com.counter.maintainer.data.contracts.CounterDetails;
import com.counter.maintainer.data.contracts.Token;

import java.util.List;

public interface CounterManager {
    Token assignTokenToCounter(Token token);

    List<CounterDetails> getCounterStatus();
}
