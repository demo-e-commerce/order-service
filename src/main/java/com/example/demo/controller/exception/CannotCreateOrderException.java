package com.example.demo.controller.exception;

public class CannotCreateOrderException extends Exception{
    public CannotCreateOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
