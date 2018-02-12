package com.counter.maintainer.exceptions;


public class TokenNotFoundException extends RuntimeException {

    public TokenNotFoundException(Long tokenId) {
        super("No Token found with tokenId:"+ tokenId);
    }
}
