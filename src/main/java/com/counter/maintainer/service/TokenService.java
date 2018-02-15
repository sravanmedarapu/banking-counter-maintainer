package com.counter.maintainer.service;

import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.data.contracts.TokenStatus;
import com.counter.maintainer.exceptions.InvalidTokenException;
import com.counter.maintainer.exceptions.TokenException;

public interface TokenService {

    Token createAndAssignToken(Token token) throws TokenException;

    Token getToken(Long tokenId) throws InvalidTokenException;

    void updateTokenStatus(Long tokenId, TokenStatus status);

    void updateCounter(Long tokenId, Long counterId);

    void addTokenToCounter(Long tokenId, Long counterId);

    Token updateTokenComments(Long tokenId, String comment);

    void updateTokenQueueStatus(Long tokenId, Long counterId, Boolean inQ);

}
