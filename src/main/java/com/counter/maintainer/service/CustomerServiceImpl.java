package com.counter.maintainer.service;

import com.counter.maintainer.data.contracts.Customer;
import com.counter.maintainer.exceptions.CustomerException;
import com.counter.maintainer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements  CustomerService{


    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Boolean isCustomerExist(Long customerId) {
        if(customerId<=0) {
            throw new CustomerException("Invalid customerId provided");
        }
        return customerRepository.isCustomerExist(customerId);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        if(customer.getName() == null ) {
            throw new CustomerException("Invalid customer name provided");
        }
        if(customer.getAddress() == null) {
            throw new CustomerException("Invalid customer address provided");
        }
        Customer customerCreated = customerRepository.save(customer);
        return customerCreated;
    }

}
