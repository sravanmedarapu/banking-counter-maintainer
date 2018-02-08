package com.counter.maintainer.service;

import com.counter.maintainer.data.contracts.CounterDetails;
import com.counter.maintainer.data.contracts.ServiceType;
import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.data.contracts.TokenStatus;

import java.util.List;

public interface CounterService {
    List<CounterDetails> findAvailableCounters(ServiceType serviceType);

    Token serveToken(Token token, long empId);

    Token updateTokenStatus(Long tokenId, TokenStatus tokenStatus, Long emplId);
}
