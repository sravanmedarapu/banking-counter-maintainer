package com.counter.maintainer.obsever.pattern;

import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.service.CounterDesk;

public interface CounterUpdatedListener {

    public void onTokenAdded (CounterDesk counterDesk);
}
