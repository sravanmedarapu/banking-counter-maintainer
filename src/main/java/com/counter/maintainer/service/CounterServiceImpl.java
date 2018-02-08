package com.counter.maintainer.service;

import com.counter.maintainer.data.contracts.*;
import com.counter.maintainer.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CounterServiceImpl implements CounterService {
    @Autowired
    private CounterRepository counterRepository;
    @Autowired
    private CounterManager counterManager;

    public List<CounterDetails> findAvailableCounters(ServiceType serviceType) {
        counterRepository.getAvailableCounters(serviceType);

        return null;
    }

    @Override

    public Token serveToken(Token token, long employeeId) {
        ServiceType serviceType = token.getServiceType();
        switch (serviceType) {
        case DEPOSIT:
        case WITHDRAW:
            //process the request
            updateTokenStatus(token.getTokenId(), TokenStatus.COMPLETED, employeeId);
            break;

        case CHECK_DEPOSIT:
            //process the request
            updateTokenStatus(token.getTokenId(), TokenStatus.PROCESSING, employeeId);
            break;
        case ACCOUNT_CLOSE:
            //verify
            updateTokenStatus(token.getTokenId(), TokenStatus.PROCESSING, employeeId);
            counterManager.assignTokenToCounter(token);
        default:
        }
        return token;
    }

    @Override
    public Token updateTokenStatus(Long tokenId, TokenStatus tokenStatus, Long emplId) {
        return null;
    }

}
