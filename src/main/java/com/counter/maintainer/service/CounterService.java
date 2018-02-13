package com.counter.maintainer.service;

import com.counter.maintainer.data.contracts.CounterDetails;
import com.counter.maintainer.data.contracts.TokenType;
import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.data.contracts.TokenStatus;

import java.util.List;

public interface CounterService {

    Token serveToken(Token token, CounterDesk counterDesk);

    Boolean updateTokenStatus(Long tokenId, TokenStatus tokenStatus, Long emplId);

    Token updateTokenComments(Long tokenId, String comment);

    List<CounterDetails> getCounterStatus();
}
