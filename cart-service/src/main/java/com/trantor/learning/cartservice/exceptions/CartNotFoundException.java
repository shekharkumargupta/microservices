package com.trantor.learning.cartservice.exceptions;

public class CartNotFoundException extends RuntimeException {

    private String message;

    public CartNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
