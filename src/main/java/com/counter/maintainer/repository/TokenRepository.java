package com.counter.maintainer.repository;

import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.data.contracts.TokenStatus;

public interface TokenRepository {
    Token createToken(Token token);
    Token getToken(long tokenId);
    void updateCounter(Long tokenId, Long counterId, Boolean inQ);
    void updateTokenComments(Long tokenId, String comment);
    void updateTokenStatus(Long tokenId, TokenStatus status, boolean inQ);

}
