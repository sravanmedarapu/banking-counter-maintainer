package com.counter.maintainer.service;

import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.data.contracts.TokenStatus;

public interface TokenService {

    Token createToken(Token token);

    void updateTokenStatus(Long tokenId, TokenStatus status, Boolean inQ);

}
