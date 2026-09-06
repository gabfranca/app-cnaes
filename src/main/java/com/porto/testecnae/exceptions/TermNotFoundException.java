package com.porto.testecnae.exceptions;

public class TermNotFoundException extends RuntimeException {
    public TermNotFoundException(String message) {
        super(message);
    }
}