package com.counter.maintainer.core;

import java.util.List;

import com.counter.maintainer.data.contracts.CounterDetails;
import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.service.CounterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CounterManager {

	@Autowired
	private CounterServiceImpl counterService;

	List<CounterDetails> counterDetails;
	
	public Token assignTicketToCounter(Token ticket) {
		//TODO:
		return null;
		
	}

}
