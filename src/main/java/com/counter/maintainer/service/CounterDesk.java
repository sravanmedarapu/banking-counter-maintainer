package com.counter.maintainer.service;

import com.counter.maintainer.data.contracts.*;
import com.counter.maintainer.exceptions.EmptyCounterQueueException;
import com.counter.maintainer.exceptions.InvalidTokenException;

import java.util.List;

public class CounterDesk extends Thread{

    private CounterService counterService;
    private CounterQueue counterQueue;
    private Long empId;
    private CounterDetails counterDetails;
    private CounterType counterType;
    private List<ServiceType> serviceTypes;

    public CounterDesk(CounterService counterService, CounterDetails counterDetails, Long empId, CounterType counterType, List<ServiceType> serviceTypes) {
        this.counterService = counterService;
        this.counterDetails = counterDetails;
        this.empId = empId;
        this.counterType = counterType;
        this.counterQueue = new CounterQueue(counterType);
        this.serviceTypes = serviceTypes;
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
        counterQueue.addToRecentServedList(servedToken);
        return servedToken;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Token token = counterQueue.fetchToken();
                serveToken(token);
            } catch (EmptyCounterQueueException e) {
                //ignore
            }
        }
    }


    public List<ServiceType> getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(List<ServiceType> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }


}
