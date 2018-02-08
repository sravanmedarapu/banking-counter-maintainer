package com.counter.maintainer.data.contracts;

import java.util.List;

public class CounterDetails {

	private long counterId;

	private List<ServiceType> serviceTypes;

	private List<Long> tokenIdList;

	private Boolean isActive;

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

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}

	public List<Long> getTokenIdList() {
		return tokenIdList;
	}

	public void setTokenIdList(List<Long> tokenIdList) {
		this.tokenIdList = tokenIdList;
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
