package com.counter.maintainer.data.contracts;

public enum ServiceType {
	
	WITHDRAWL(5), DEPOSIT(5), CHECKDEPISIT(10), ACCOUNT_CLOSE(5);
	
	
	int timeInMin;
	
	ServiceType(int timeInMin) {
		this.timeInMin = timeInMin;
	}
	
	
	

}
