package com.counter.maintainer.obsever.pattern;

import com.counter.maintainer.data.contracts.*;
import com.counter.maintainer.exceptions.CountersNotAvailableException;
import com.counter.maintainer.repository.CounterRepository;
import com.counter.maintainer.repository.EmployeeRepository;
import com.counter.maintainer.service.CounterDesk;
import com.counter.maintainer.service.CounterService;
import com.counter.maintainer.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.counter.maintainer.data.contracts.ServiceType.*;
import static com.counter.maintainer.data.contracts.ServiceType.CHECK_DEPOSIT;
import static com.counter.maintainer.data.contracts.ServiceType.DOC_VERIFICATION;

@Service
public class CounterMGR {
    private List<CounterDesk> counterList = new ArrayList<>();
    private CounterUpdatedListener listener = new CounterUpdated();



    @Autowired
    protected CounterService counterService;

    @Autowired
    private CounterRepository counterRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmployeeRepository employeeRepository;

    ExecutorService executor;

    @EventListener(ApplicationReadyEvent.class)
    public void initCounters() {
        List<CounterDetails> counterDetailsList = counterRepository.getAvailableCounters();

        if(counterDetailsList.isEmpty()) {
            throw new RuntimeException("CounterDetails not available exception");
        }
        executor = Executors.newFixedThreadPool(counterDetailsList.size());
        for(CounterDetails counterDetails: counterDetailsList) {
            List<ServiceType> serviceTypes = getServiceTypeList(counterDetails.getEmployeeId());
            CounterUpdated counterUpdated = new CounterUpdated();
            CounterDesk counterDesk = new CounterDesk(counterService, counterDetails, counterDetails.getEmployeeId(), counterDetails.getCounterType(),
                                                      serviceTypes, counterUpdated);
           // executor.execute(counterDesk);
            counterList.add(counterDesk);
        }
    }


    public CompletableFuture addToken(Token token) {
        CounterDesk counterDesk = assignToken(token);

        CompletableFuture cf = new CompletableFuture<>();

        // Run a task specified by a Supplier object asynchronously
        cf = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                    listener.onTokenAdded(counterDesk);
                return "";
            }
        });

        return cf;
    }


    @Async
    public void notifyCounterUpdatedListeners(CounterDesk counterDesk) {
        // Notify each of the listeners in the list of registered listeners

      //  CompletableFuture.runAsync(listener.onTokenAdded(counterDesk))
        this.listener.onTokenAdded(counterDesk);
    }

    public CounterDesk assignToken(Token token) {
        List<CounterDesk> counterDesks = getAvailableCounterDesks(token);
        if(counterDesks.isEmpty()) {
            throw new CountersNotAvailableException();
        }
        Integer minQueueLength = Integer.MAX_VALUE;
        CounterDesk minCounterDesk = counterDesks.get(0);
        for(CounterDesk counterDesk : counterDesks) {
            int curMinLength = counterDesk.getMinQueueLength(token.getServicePriority());
            if (minQueueLength > curMinLength) {
                minQueueLength = curMinLength;
                minCounterDesk = counterDesk;
                if (minQueueLength == 0) {
                    //found empty queue, no need to search other queues
                    break;
                }
            }
        }

        addTokenToCounterQueue(minCounterDesk, token);
        //tokenService.updateTokenStatus(token.getTokenId(), TokenStatus.QUEUED);
        return minCounterDesk;

    }

    private void addTokenToCounterQueue(CounterDesk counter, Token token) {
        counter.addTokenToQueue(token);
        token.setCounterId(counter.getCounterId());
        tokenService.addTokenToCounter(token.getTokenId(), token.getCounterId());
        token.setStatus(TokenStatus.QUEUED);
    }


    private List<CounterDesk> getAvailableCounterDesks( Token token) {
        if(token.getServicePriority() == ServicePriority.PREMIUM) {
            return counterList.stream().filter(counterDesk ->
                                                   counterDesk.getCounterType() == CounterType.BOTH
                                                       || counterDesk.getCounterType() == CounterType.PREMIUM
            ).filter(counterDesk -> {
                return counterDesk.getServiceTypes().contains(token.peekNextServiceType());
            }).collect(Collectors.toList());
        } else {
            return counterList.stream().filter(counterDesk ->
                                                   counterDesk.getCounterType() == CounterType.BOTH
                                                       || counterDesk.getCounterType() == CounterType.REGULAR
            ).filter(counterDesk -> {
                return counterDesk.getServiceTypes().contains(token.peekNextServiceType());
            }).collect(Collectors.toList());
        }
    }



    public List<ServiceType> getServiceTypeList(Long employeeId) {
        EmployeeRole role = employeeRepository.getEmployeeRole(employeeId);
        switch (role) {
        case MANAGER:
            return Arrays.asList(ACC_VERIFICATION, BALANCE_ENQUIRY, ACC_CLOSE, ACC_OPEN, MANAGER_APPROVAL, DOC_VERIFICATION);
        case OPERATOR:
            return Arrays.asList(ACC_VERIFICATION,BALANCE_ENQUIRY,CASH_WITHDRAW,CASH_DEPOSIT,CHECK_DEPOSIT, DOC_VERIFICATION);
        default:
            throw new RuntimeException("Unknown EmployeeRole :" + role.name());
        }

    }
}
