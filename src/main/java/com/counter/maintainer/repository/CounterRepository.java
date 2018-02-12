package com.counter.maintainer.repository;

import com.counter.maintainer.data.contracts.CounterDetails;
import com.counter.maintainer.data.contracts.TokenType;

import java.util.List;

public interface CounterRepository {
    List<CounterDetails> getCountersStatus();

    List<TokenType> findCounterServices(long counterId);

    List<Long> findCounterTokens(long counterId);

    List<CounterDetails> getAvailableCounters(TokenType tokenType);

    List<CounterDetails> getAvailableCounters();

}
