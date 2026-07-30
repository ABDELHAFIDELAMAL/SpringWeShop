package com.example.demo.exception;

public class AllreadyExistException extends RuntimeException{
    public AllreadyExistException(String message) {
        super(message);
    }
}
