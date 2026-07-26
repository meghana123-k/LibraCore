package com.libracore.exception;

public class InvalidBookReturnException extends RuntimeException {

    public InvalidBookReturnException(String message) {
        super(message);
    }
}