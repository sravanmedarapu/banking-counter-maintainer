package com.counter.maintainer.data.contracts;

import java.util.List;

public class CounterDetails {

	private long counterId;

	private List<TokenType> tokenTypes;

	private List<Long> tokenIdList;

	private Boolean isActive;

	private long employeeId;

	private CounterType counterType;

	public long getCounterId() {
		return counterId;
	}


	public void setCounterId(long counterId) {
		this.counterId = counterId;
	}


	public List<TokenType> getTokenTypes() {
		return tokenTypes;
	}


	public void setTokenTypes(List<TokenType> tokenTypes) {
		this.tokenTypes = tokenTypes;
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

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public CounterType getCounterType() {
		return counterType;
	}

	public void setCounterType(CounterType counterType) {
		this.counterType = counterType;
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
