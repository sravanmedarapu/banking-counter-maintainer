package com.counter.maintainer.service;

import com.counter.maintainer.data.contracts.*;
import com.counter.maintainer.exceptions.CountersNotAvailableException;
import com.counter.maintainer.repository.CounterRepository;
import com.counter.maintainer.repository.EmployeeRepository;
import com.counter.maintainer.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CounterManagerImpl implements CounterManager {

    private List<CounterDesk> counterList = new ArrayList<>();

    @Autowired
    protected CounterService counterService1;

    @Autowired
    private CounterRepository counterRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        List<CounterDetails> counterDetailsList = counterRepository.getAvailableCounters();
        List<Employee> employeeList= employeeRepository.getEmployees();

        if(counterDetailsList.isEmpty()) {
            throw new RuntimeException("CounterDetails not available exception");
        }
        for(CounterDetails counterDetails: counterDetailsList) {
            //TODO: update to fetch and empId and counterType
            CounterDesk counterDesk = new CounterDesk(employeeList.get(0).getEmployeeId(), CounterType.BOTH);
            counterDesk.start();
            counterList.add(counterDesk);
        }
    }
/*
    @Autowired
    CounterManagerImpl() {


    }*/

    @Override
    public Token assignTokenToCounter(Token token) {

        return assignToken(token);

    }

    private Token assignToken(Token token) {
        List<CounterDesk> counterDesks = getAvailableCounterDesks(token.getServicePriority());
        if(counterDesks.isEmpty()) {
            throw new CountersNotAvailableException();
        }
        if(token.getServicePriority() == ServicePriority.PREMIUM) {
            Integer minQueueLength = Integer.MIN_VALUE;
            CounterDesk minCounterDesk = counterDesks.get(0);
            for(CounterDesk counterDesk : counterDesks) {
                int curMinLegth = counterDesk.getMinQueueLength(token.getServicePriority());
                if(minQueueLength > curMinLegth) {
                    minQueueLength = curMinLegth;
                    minCounterDesk = counterDesk;
                    if(minQueueLength == 0) {
                        //found empty queue no need to search other queues
                        break;
                    }
                }
            }
            minCounterDesk.addTokenToQueue(token);
            token.setCounterId(minCounterDesk.getCounterId());
        }

        return token;

    }

    private List<CounterDesk> getAvailableCounterDesks( ServicePriority servicePriority) {
        if(servicePriority == ServicePriority.PREMIUM) {
            return counterList.stream().filter(counterDesk ->
                          counterDesk.getCounterType() == CounterType.BOTH
                              || counterDesk.getCounterType() == CounterType.PREMIUM
            ).collect(Collectors.toList());
        } else {
            return counterList.stream().filter(counterDesk ->
                          counterDesk.getCounterType() == CounterType.BOTH
                              || counterDesk.getCounterType() == CounterType.REGULAR
            ).collect(Collectors.toList());
        }
    }


}
