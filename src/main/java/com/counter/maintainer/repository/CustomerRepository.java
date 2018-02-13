package com.counter.maintainer.repository;

import com.counter.maintainer.data.contracts.Customer;

public interface CustomerRepository {
    Customer save(Customer customer);
    Boolean isCustomerExist(Long customerId);
}
