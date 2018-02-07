package com.counter.maintainer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.counter.maintainer.data.contracts.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	/*
	@Query("select c.customerId as customerId, c.name as name, c.phoneNumber as phoneNumber, c.typeOfService as typeOfService,"
			+ " add.streeName as streeName, add.city as city, add.state as state, add.zipCode as zipCode, add.country as country"
			+ " from customer c"
			+ " left outer join address add "
			+ " on c.customerId= add.customerId")
			*/
			
	//Customer findCustomerById(Long customerId);
	
	Customer save(Customer customer);
	
	List<Customer> findAll();
	
	Customer findOne(Long customerId);
	

}
