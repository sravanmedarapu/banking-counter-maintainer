package com.counter.maintainer.service;

import com.counter.maintainer.data.contracts.*;
import com.counter.maintainer.exceptions.EmptyCounterQueueException;
import com.counter.maintainer.exceptions.InvalidTokenException;
import com.counter.maintainer.obsever.pattern.CounterUpdated;
import com.counter.maintainer.obsever.pattern.CounterUpdatedListener;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CounterDesk extends Thread{

    private CounterService counterService;
    private CounterQueue counterQueue;
    private Long empId;
    private CounterDetails counterDetails;
    private CounterType counterType;
    private List<ServiceType> serviceTypes;
    private boolean isProcessing = false;
    private CounterUpdatedListener counterUpdated;
    ReadWriteLock lock = new ReentrantReadWriteLock();

    public CounterDesk(CounterService counterService, CounterDetails counterDetails, Long empId, CounterType counterType, List<ServiceType> serviceTypes, CounterUpdatedListener counterUpdated) {
        this.counterService = counterService;
        this.counterDetails = counterDetails;
        this.empId = empId;
        this.counterType = counterType;
        this.counterQueue = new CounterQueue(counterType);
        this.serviceTypes = serviceTypes;
        this.counterUpdated=counterUpdated;
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
        Token servedToken = counterService.serveToken(token, this);
        counterQueue.addToRecentServedList(servedToken);
        return servedToken;
    }

    public boolean isProcessing() {
        return isProcessing;
    }

    public void setProcessing(boolean processing) {
        isProcessing = processing;
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

    public void processQueue() {
        lock.readLock().lock();
        while (counterQueue.getQueueLength() >0) {
            try {
                Token token = counterQueue.fetchToken();
                serveToken(token);
            } catch (EmptyCounterQueueException e) {
                break;
            }
        }
        lock.readLock().unlock();
    }


    public List<ServiceType> getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(List<ServiceType> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }


}
