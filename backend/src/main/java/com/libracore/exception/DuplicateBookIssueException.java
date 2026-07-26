package com.libracore.exception;

public class DuplicateBookIssueException extends RuntimeException {

    public DuplicateBookIssueException(String message) {
        super(message);
    }
}