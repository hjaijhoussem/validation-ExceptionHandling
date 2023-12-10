package com.example.demo.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmailAlreadyExistException extends RuntimeException{
    public EmailAlreadyExistException(String message){super(message);}
}
