package com.counter.maintainer.service;

import com.counter.maintainer.data.contracts.Customer;
import com.counter.maintainer.data.contracts.ServicePriority;
import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.data.contracts.TokenStatus;
import com.counter.maintainer.exceptions.InvalidTokenException;
import com.counter.maintainer.repository.CounterRepository;
import com.counter.maintainer.repository.CustomerRepository;
import com.counter.maintainer.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class TokenServiceImpl implements TokenService{

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private CounterManager counterManager;

    @Autowired
    private CustomerService customerService;

    @Transactional
    public Token createToken(Token token) {
        if (token.getCustomerId() == null || customerService.isCustomerExist(token.getCustomerId())) {
            Customer customer = customerService.createCustomer(token.getCustomer());
            token.setCustomer(customer);
            token.setCustomerId(customer.getCustomerId());
        }
        Token createdToken = tokenRepository.createToken(token);

        return counterManager.assignTokenToCounter(createdToken);
    }

    public void updateTokenStatus(Long tokenId, TokenStatus status) {
        tokenRepository.updateTokenStatus(tokenId, status);
    }

    @Override
    public void updateCounter(Long tokenId, Long counterId) {
        tokenRepository.updateCounter(tokenId, counterId);
    }

    @Override
    public void addTokenToCounter(Long tokenId, Long counterId) {
        tokenRepository.addTokenToCounter(tokenId, counterId);
    }

    @Override
    public Token updateTokenComments(Long tokenId, String comment) {
        tokenRepository.updateTokenComments(tokenId, comment);
        return tokenRepository.getToken(tokenId);
    }


    @Override
    public void updateTokenQueueStatus(Long tokenId, Long counterId, Boolean inQ) {
        tokenRepository.updateTokenQueueStatus(tokenId, counterId, inQ);
    }

    @Override
    public Token getToken(Long tokenId) {
        if (tokenId <= 0) {
            throw new InvalidTokenException("Invalid tokenId:" + tokenId);
        }
        return tokenRepository.getToken(tokenId);
    }
}
