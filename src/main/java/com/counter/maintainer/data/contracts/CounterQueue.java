package com.counter.maintainer.data.contracts;

import com.counter.maintainer.exceptions.EmptyCounterQueueException;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.PriorityQueue;

public class CounterQueue {
    private PriorityQueue<Token> regularQueue = new PriorityQueue<Token>();
    private PriorityQueue<Token> premiumQueue = new PriorityQueue<Token>();
    private final CounterType counterQueueType;
    private int premiumQueueServeRatio;
    private int regularQueueServeRatio;
    private CircularFifoQueue recentServedServiceList;
    private String serveRatio = "2:1";//TODO: read serveRatio from application properties

    @Autowired
    private Environment env;

    public CounterQueue(CounterType counterQueueType) {
        this.counterQueueType = counterQueueType;

        if(counterQueueType.equals(CounterType.BOTH)) {
            if (serveRatio == null || serveRatio.isEmpty()) {
                throw new RuntimeException("Should pass serveRation for CounterType.BOTH in regular:premium format ex:1:2");
            } else if(serveRatio.split(":").length==2) {
                regularQueueServeRatio = Integer.valueOf(serveRatio.split(":")[0]);
                premiumQueueServeRatio = Integer.valueOf(serveRatio.split(":")[1]);
                recentServedServiceList = new CircularFifoQueue<ServicePriority>(premiumQueueServeRatio);
            } else {
                throw new RuntimeException("regular, premium serve ration not defined properly, correct format regular:premium ex: 1:2");
            }
        }
    }

    public Token addTokenToQueue(Token token) {
        token.setCounterAddedTime(DateTime.now());
        if(counterQueueType.equals(CounterType.PREMIUM)
               && token.getServicePriority().equals(ServicePriority.REGULAR)) {
            throw new RuntimeException("This is PREMIUM counter, REGULAR customers will not be served here");

        } else {
            if(token.getServicePriority().equals(ServicePriority.REGULAR)) {
                regularQueue.add(token);
            } else {
                premiumQueue.add(token);
            }
        }
        return token;
    }

    public void addToRecentServedList(ServicePriority priority) {
        if(counterQueueType.equals(CounterType.BOTH)) {
            recentServedServiceList.add(priority);
        }
    }

    public Token fetchToken() throws EmptyCounterQueueException {

        if(premiumQueue.isEmpty() && regularQueue.isEmpty()) {
            throw new EmptyCounterQueueException();
        }

        switch (counterQueueType) {
        case PREMIUM:
            return premiumQueue.poll();
        case REGULAR:
            return regularQueue.poll();
        case BOTH:
            return fetchTokenFromBothCounter();
        default:
            throw new RuntimeException("unknown counterType:"+counterQueueType);

        }
    }

    private Token fetchTokenFromBothCounter() throws EmptyCounterQueueException {
        if((recentServedServiceList.isEmpty() || recentServedServiceList.contains(ServicePriority.REGULAR))
            && premiumQueue.size()>0) {
            return premiumQueue.poll();
        } else if(regularQueue.size()>0) {
            return regularQueue.poll();
        } else {
            throw new EmptyCounterQueueException();
        }
    }

    public Integer getQueueLength() {
        return regularQueue.size() + premiumQueue.size();
    }


    public Integer getMinQueueLength(ServicePriority priority) {
        if(priority == ServicePriority.REGULAR
               || counterQueueType == CounterType.PREMIUM
               || counterQueueType == CounterType.REGULAR ) {
            return getQueueLength();
        } else {

            int minQueueForPremium = (premiumQueue.size()>0 ? premiumQueue.size()/premiumQueueServeRatio : 0);
            if(minQueueForPremium >= regularQueue.size()) {
                minQueueForPremium = minQueueForPremium + (minQueueForPremium - regularQueue.size());

            }
            return minQueueForPremium;
        }
    }
}
