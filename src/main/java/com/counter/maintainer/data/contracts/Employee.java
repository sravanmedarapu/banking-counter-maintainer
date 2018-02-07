package com.counter.maintainer.data.contracts;

public class Employee {
	
	private long employeeId;
	private EmployeeRole role;
	
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public EmployeeRole getRole() {
		return role;
	}
	public void setDesignation(EmployeeRole role) {
		this.role = role;
	}
	
}
