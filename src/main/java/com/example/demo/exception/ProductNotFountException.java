package com.example.demo.exception;

public class ProductNotFountException extends RuntimeException{
    public ProductNotFountException(String message) {
        super(message);
    }
}
