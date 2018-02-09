package com.counter.maintainer.service;

import com.counter.maintainer.data.contracts.Customer;
import com.counter.maintainer.data.contracts.ServicePriority;
import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.data.contracts.TokenStatus;
import com.counter.maintainer.repository.CounterRepository;
import com.counter.maintainer.repository.CustomerRepository;
import com.counter.maintainer.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class TokenServiceImpl {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private CounterRepository counterRepository;

    @Autowired
    private CounterManager counterManager;

    @Autowired
    private CustomerService customerService;

    @Transactional
    public Token createToken(Token token) {
        if (token.getCustomerId() == null) {
            Customer customer = customerService.createCustomer(token.getCustomer());
            token.setCustomer(customer);
            token.setCustomerId(customer.getCustomerId());
        }
        Token createdToken = tokenRepository.createToken(token);

        return counterManager.assignTokenToCounter(createdToken);
    }

    public void updateTokenStatus(Long tokenId, TokenStatus status, Boolean inQ) {
        tokenRepository.updateTokenStatus(tokenId, status, inQ);
    }

    public Token getToken(Long tokenId) {
        if (tokenId <= 0) {
            throw new RuntimeException("Invalid tokenId:" + tokenId);
        }
        return tokenRepository.getToken(tokenId);
    }
}
