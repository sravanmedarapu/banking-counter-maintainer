package com.counter.maintainer.data.contracts;

public enum ServiceType {
	
	WITHDRAW(5), DEPOSIT(5), CHECK_DEPOSIT(10), ACCOUNT_CLOSE(5);
	
	int avgTimeInMin;
	
	ServiceType(int avgTimeInMin) {
		this.avgTimeInMin = avgTimeInMin;
	}

	

}
