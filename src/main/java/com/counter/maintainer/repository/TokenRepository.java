package com.counter.maintainer.repository;

import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.data.contracts.TokenStatus;
import com.counter.maintainer.exceptions.RepositoryException;

public interface TokenRepository {
    Token createToken(Token token) throws RepositoryException;
    Token getToken(long tokenId);
    void updateCounter(Long tokenId, Long counterId);
    void addTokenToCounter(Long tokenId, Long counterId);
    void updateTokenComments(Long tokenId, String comment);
    void updateTokenStatus(Long tokenId, TokenStatus status);
    void updateTokenQueueStatus(Long tokenId, Long counterId, Boolean inQ);
    Boolean isTokenExist(long tokenId);

}
