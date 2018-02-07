package com.counter.maintainer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.counter.maintainer.data.contracts.Customer;
import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.repository.CustomerRepository;
import com.counter.maintainer.service.CustomerService;

@RestController
@RequestMapping("/api")
public class TokenController {
	
	@Autowired
	CustomerService customerService;
	

	@RequestMapping(method=RequestMethod.POST, value="/token/employee/{employeeId}/update")
	public boolean updateTokenStatus(@RequestBody Token token, @PathVariable long employeeId) {
		//TODO: yet to implement
		return false;
	}
	

	/**
	 * check if the customer is existing or new
	 * if he is new customer then collect CustomerDetials
	 */
	@RequestMapping(method=RequestMethod.POST, value="/token/create")
	public Token createToken(@RequestBody Token token) {
		Token createdToken = new Token();
		if(token.getCustomerId() == null) {
			Customer customer = customerService.createCustomer(token.getCustomer());
			createdToken.setCustomer(customer);
			System.out.println(customer.getCustomerId());
		}
		return createdToken;
	}
	
	
	

}
