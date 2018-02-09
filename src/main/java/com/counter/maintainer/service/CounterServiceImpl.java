package com.counter.maintainer.service;

import com.counter.maintainer.data.contracts.*;
import com.counter.maintainer.repository.CounterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounterServiceImpl implements CounterService {

    @Autowired
    private CounterRepository counterRepository;
    @Autowired
    private CounterManager counterManager;
    @Autowired
    private TokenManager tokenManager;

    private static final Logger logger = LoggerFactory.getLogger(CounterServiceImpl.class);

    public List<CounterDetails> getAvailableCounters(ServiceType serviceType) {
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
            logger.info("Processing %s request, will be completed in 1 min",serviceType.name());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateTokenStatus(token.getTokenId(), TokenStatus.COMPLETED, employeeId);
            break;

        case CHECK_DEPOSIT:
            //process the request
            logger.info("Processing %s request, will be completed in 2 min",serviceType.name());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateTokenStatus(token.getTokenId(), TokenStatus.PROCESSING, employeeId);
            break;
        case ACCOUNT_CLOSE:
            //verify
            logger.info("Processing %s request, will be completed in 3 min",serviceType.name());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counterManager.assignTokenToCounter(token);
            updateTokenStatus(token.getTokenId(), TokenStatus.PROCESSING, employeeId);
        default:
        }
        return token;
    }

    @Override
    public Boolean updateTokenStatus(Long tokenId, TokenStatus tokenStatus, Long emplId) {


        if(tokenStatus == TokenStatus.COMPLETED || tokenStatus == TokenStatus.CANCELLED) {
            tokenManager.updateTokenStatus(tokenId, tokenStatus, false);
        } else {
            tokenManager.updateTokenStatus(tokenId, tokenStatus, true);
        }

        return true;
    }

}
