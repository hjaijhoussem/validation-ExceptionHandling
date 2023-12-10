package com.example.demo.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoSuchCustomerExistsException extends RuntimeException{
    public NoSuchCustomerExistsException(ErrorEnum errorEnum) {super(errorEnum.getMessage());}

}
