package com.counter.maintainer.service;

import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.data.contracts.TokenStatus;

public interface TokenManager {
    Token assignTokenToCounter(Token token);

    void updateTokenStatus(Long tokenId, TokenStatus status, Boolean inQ);

    Token getToken(Long tokenId);

}
