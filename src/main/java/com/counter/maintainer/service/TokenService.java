package com.counter.maintainer.service;

import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.data.contracts.TokenStatus;

public interface TokenService {

    Token createToken(Token token);

    Token getToken(Long tokenId);

    void updateTokenStatus(Long tokenId, TokenStatus status);

    void updateCounter(Long tokenId, Long counterId);

    void addTokenToCounter(Long tokenId, Long counterId);

    Token updateTokenComments(Long tokenId, String comment);

    void updateTokenQueueStatus(Long tokenId, Long counterId, Boolean inQ);

}
