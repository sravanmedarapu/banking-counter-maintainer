package com.counter.maintainer.data.contracts;

import java.util.List;

public class Counter {
	private long counterId;
	private List<ServiceType> serviceTypes;
	private CounterStatus counterStatus;
	private List<Token> tokens;
	
	public List<Token> getTokens() {
		return tokens;
	}


	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}


	public long getCounterId() {
		return counterId;
	}


	public void setCounterId(long counterId) {
		this.counterId = counterId;
	}


	public List<ServiceType> getServiceTypes() {
		return serviceTypes;
	}


	public void setServiceTypes(List<ServiceType> serviceTypes) {
		this.serviceTypes = serviceTypes;
	}


	public void setCounterStatus(CounterStatus counterStatus) {
		this.counterStatus = counterStatus;
	}


	public CounterStatus getCounterStatus() {
		//TODO: yet to implement
		return null;
		
	}
	
}

class CounterStatus {
	List<Token> tokens;

	public List<Token> getTokens() {
		return tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}
	
	
}
