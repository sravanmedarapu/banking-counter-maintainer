package com.counter.maintainer.service;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.counter.maintainer.data.contracts.Customer;
import com.counter.maintainer.repository.CustomerRepository;

@Component
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	@Transactional
	public Customer createCustomer(Customer customer) {
		Customer customerCreated = customerRepository.save(customer);
		return customerCreated;
	}

}
