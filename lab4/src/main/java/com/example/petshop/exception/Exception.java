package com.example.petshop.exception;

import org.springframework.beans.factory.annotation.Autowired;

public class Exception extends RuntimeException {
    @Autowired
    public Exception(String message) {
        super(message);
    }
}