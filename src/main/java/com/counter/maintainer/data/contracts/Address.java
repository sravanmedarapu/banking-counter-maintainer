package com.counter.maintainer.data.contracts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.domain.EntityScan;


public class Address {
	/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")*/
	private String id;
	
	//@Column(name = "streeName")
	private String streeName;
	
	//@Column(name = "city")
	private String city;
	
	//@Column(name = "state")
	private String state;
	
	//@Column(name = "country")
	private String country;
	
	//@Column(name = "zipCode")
	private String zipCode;
	
	public String getStreeName() {
		return streeName;
	}
	public void setStreeName(String streeName) {
		this.streeName = streeName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	
}
