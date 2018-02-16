package com.counter.maintainer.controller;

import com.counter.maintainer.exceptions.InvalidCustomerException;
import com.counter.maintainer.exceptions.TokenException;
import com.counter.maintainer.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.service.CustomerService;

@RestController
public class TokenController {
	
	@Autowired
	CustomerService customerService;

	@Autowired
	TokenService tokenService;


	/**
	 * Creates Token, If the customer not exist then will create new customer before creating token
	 *
	 * @throws InvalidCustomerException if provided customerId not valid
	 * @throws TokenException if token is not created or assigned to counter
	 * @return Token
	 */
	@RequestMapping(method=RequestMethod.POST, value="/api/token/create")
	public Token createToken(@RequestBody Token token) {
		return tokenService.createAndAssignToken(token);
	}

	/**
	 * Returns Token for given tokenId
	 *
	 * @param tokenId
	 * @return Token
	 * throws InvalidTokenException if tokenId is invalid
	 */
	@RequestMapping(method=RequestMethod.GET, value="/api/token/{tokenId}")
	public Token getToken(@PathVariable long tokenId) {
		return tokenService.getToken(tokenId);
	}
	

}
