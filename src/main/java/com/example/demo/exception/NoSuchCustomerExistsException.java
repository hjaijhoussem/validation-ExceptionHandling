package com.example.demo.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoSuchCustomerExistsException extends RuntimeException{
    public NoSuchCustomerExistsException(String msg) {super(msg);}
}
