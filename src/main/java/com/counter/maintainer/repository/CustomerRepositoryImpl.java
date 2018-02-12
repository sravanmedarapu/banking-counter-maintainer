package com.counter.maintainer.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.counter.maintainer.data.contracts.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.counter.maintainer.data.contracts.Customer;

@Repository
public class CustomerRepositoryImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	public Customer save(Customer customer) {
		KeyHolder idHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

				PreparedStatement ps = connection.prepareStatement("insert into customer  (name, phoneNumber) values (?1, ?2)",
																   new String[]{"id"});

				ps.setString(1, customer.getName());
				ps.setString(2, customer.getPhoneNumber());
				return ps;
			}
		}, idHolder);

		customer.setCustomerId(idHolder.getKey().intValue());
		Address address = customer.getAddress();
		if(address != null) {

			jdbcTemplate.update("insert into address set customerId=?1, city=?2, state=?3, country=?4, zipcode=?5",
								customer.getCustomerId(), address.getCity(), address.getState(), address.getCountry(), address.getZipCode());
		}
		return customer;
	}
	
	public List<Customer> findAll() {
		return null;
	}
	
	public Customer findOne(Long customerId) {
		return null;
	}
	

}
