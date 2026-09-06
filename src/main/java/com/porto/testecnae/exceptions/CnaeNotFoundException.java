package com.porto.testecnae.exceptions;

public class CnaeNotFoundException extends RuntimeException {
    public CnaeNotFoundException(String message) {
        super(message);
    }
}