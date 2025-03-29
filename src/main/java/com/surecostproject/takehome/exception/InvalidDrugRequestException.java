package com.surecostproject.takehome.exception;

public class InvalidDrugRequestException extends RuntimeException {
    public InvalidDrugRequestException(String message) {
        super(message);
    }
} 