package com.counter.maintainer.data.contracts;
import java.io.Serializable;

import javax.persistence.*;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.repository.NoRepositoryBean;
/*
@Entity
@Table(name = "customer")*/
public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="customerId")*/
	private long customerId;

	// @Column(name="name")
	private String name;

	//@Column(name="phoneNumber")
	private String phoneNumber;

	//@ManyToOne(optional = false,fetch = FetchType.EAGER,cascade= CascadeType.ALL)
	//@JoinColumn(name = "id", nullable=false)
	private Address address;

	//@Column(name="servicePriority")
	//@Enumerated(EnumType.ORDINAL)
	private ServicePriority servicePriority;
	
	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public ServicePriority getTypeOfService() {
		return servicePriority;
	}
	public void setTypeOfService(ServicePriority servicePriority) {
		this.servicePriority = servicePriority;
	}
	
	

}
