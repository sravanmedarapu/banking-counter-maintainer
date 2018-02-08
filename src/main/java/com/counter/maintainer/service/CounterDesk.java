package com.counter.maintainer.service;

import com.counter.maintainer.data.contracts.*;
import com.counter.maintainer.exceptions.EmptyCounterQueueException;
import com.counter.maintainer.exceptions.InvalidTokenException;
import org.springframework.beans.factory.annotation.Autowired;

public class CounterDesk extends Thread{

    @Autowired
    private CounterService counterService;
    private CounterQueue counterQueue;
    private Boolean isFree = false;
    private Long empId;
    private CounterDetails counterDetails;
    private Boolean isCounterOpen = true;
    private CounterType counterType;

    public CounterDesk(Long empId, CounterType counterType) {
        this.empId = empId;
        this.counterType = counterType;
        this.counterQueue = new CounterQueue(counterType);
    }

    public CounterDetails getCounterDetails() {
        return counterDetails;
    }

    public void setCounterDetails(CounterDetails counterDetails) {
        this.counterDetails = counterDetails;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public CounterType getCounterType() {
        return counterType;
    }

    public void setCounterType(CounterType counterType) {
        this.counterType = counterType;
    }

    public Integer getQueueLength() {
        return counterQueue.getQueueLength();
    }

    public Long getCounterId() {
        return counterDetails.getCounterId();
    }

    public Integer getMinQueueLength(ServicePriority priority) {
        return counterQueue.getMinQueueLength(priority);
    }

    public Token addTokenToQueue(Token token) {
       return counterQueue.addTokenToQueue(token);
    }

    private Token serveToken(Token token) {
        if(token.getStatus() == TokenStatus.CANCELLED
               || token.getStatus() == TokenStatus.COMPLETED) {
            throw new InvalidTokenException("Can't process " + token.getStatus().name() + "token");
        }
        Token servedToken = counterService.serveToken(token, empId);
        counterQueue.addToRecentServedList(servedToken.getServicePriority());
        return servedToken;
    }

    @Override
    public void run() {
        while (true) {
            if(isFree) {
                try {
                    Token token = counterQueue.fetchToken();
                    serveToken(token);
                } catch (EmptyCounterQueueException e) {
                    //ignore
                }
            }
        }
    }

}
