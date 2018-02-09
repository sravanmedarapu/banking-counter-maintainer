package com.counter.maintainer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.counter.maintainer.data.contracts.Customer;
import com.counter.maintainer.service.CustomerService;

@RestController
public class CounterController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/counter/status")
	public List<Long> getCounterStatus() {
		//TODO: yet to implement
		return null;

	}

	@RequestMapping(method=RequestMethod.GET, value="/counter/status")
	public String getStatus() {
		//TODO: yet to implement
		return null;
	}

	public void createCustomer(Customer customer) {
		customerService.createCustomer(customer);
	}
	
	

}
