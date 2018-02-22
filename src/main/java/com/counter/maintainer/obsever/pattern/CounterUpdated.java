package com.counter.maintainer.obsever.pattern;

import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.service.CounterDesk;
import com.counter.maintainer.service.CounterServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public class CounterUpdated implements CounterUpdatedListener {

    private static final Logger logger = LoggerFactory.getLogger(CounterUpdated.class);

    public void onTokenAdded(CounterDesk counterDesk) {
        processQueue(counterDesk);

    }

    public void processQueue(CounterDesk counterDesk) {
        logger.debug("> process Counter Queue");
        counterDesk.processQueue();
        logger.debug("> Counter Queue processing completed");


    }
}
