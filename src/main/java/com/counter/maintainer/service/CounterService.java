package com.counter.maintainer.service;

import com.counter.maintainer.data.contracts.CounterDetails;
import com.counter.maintainer.data.contracts.TokenType;
import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.data.contracts.TokenStatus;

import java.util.List;

public interface CounterService {
    List<CounterDetails> getAvailableCounters(TokenType tokenType);

    Token serveToken(Token token, long empId);

    Boolean updateTokenStatus(Long tokenId, TokenStatus tokenStatus, Long emplId);

    List<CounterDetails> getCounterStatus();
}
