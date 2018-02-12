package com.counter.maintainer.controller;

import com.counter.maintainer.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.counter.maintainer.data.contracts.Customer;
import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.service.CustomerService;

@RestController
public class TokenController {
	
	@Autowired
	CustomerService customerService;

	@Autowired
	TokenService tokenService;


	/**
	 * Creates new Token, If the customer not exist then will create new customer before creating token
	 *
	 */
	@RequestMapping(method=RequestMethod.POST, value="/token/create")
	public Token createToken(@RequestBody Token token) {
		return tokenService.createToken(token);
	}

	/**
	 * Return Token
	 * @param tokenId
	 * throws InvalidTokenException if tokenId is invalid
	 */
	@RequestMapping(method=RequestMethod.GET, value="/token/{tokenId}")
	public Token getToken(@PathVariable long tokenId) {
		return tokenService.getToken(tokenId);
	}
	

}
