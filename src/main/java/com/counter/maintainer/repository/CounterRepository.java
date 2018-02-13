package com.counter.maintainer.repository;

import com.counter.maintainer.data.contracts.CounterDetails;
import com.counter.maintainer.data.contracts.ServiceType;
import com.counter.maintainer.data.contracts.TokenType;

import java.util.List;

public interface CounterRepository {

    List<ServiceType> findCounterServices(long counterId);

    List<Long> findCounterTokens(long counterId);

    List<CounterDetails> getAvailableCounters();

}
