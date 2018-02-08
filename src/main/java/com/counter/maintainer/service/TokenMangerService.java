package com.counter.maintainer.service;

import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.data.contracts.TokenStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class TokenMangerService implements TokenManager{

    @Autowired
    private CounterManager counterManager;

    @Override
    public Token assignTokenToCounter(Token token) {
        return counterManager.assignTokenToCounter(token);
    }

}
