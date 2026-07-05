package com.deneme.customer_service.core.exceptions;

public class CustomerAlreadyExistsException extends RuntimeException{

    public CustomerAlreadyExistsException(String message){
        super(message);
    }
}
