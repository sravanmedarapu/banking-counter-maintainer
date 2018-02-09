package com.counter.maintainer.service;

import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.data.contracts.TokenStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenMangerService implements TokenManager{

    @Autowired
    private CounterManager counterManager;

    @Autowired
    private TokenService tokenService;

    @Override
    public Token assignTokenToCounter(Token token) {
        return counterManager.assignTokenToCounter(token);
    }

    @Override
    public void updateTokenStatus(Long tokenId, TokenStatus status, Boolean inQ) {
         tokenService.updateTokenStatus(tokenId, status, inQ);
    }

    @Override
    public Token getToken(Long tokenId) {
        return tokenService.getToken(tokenId);
    }

}
