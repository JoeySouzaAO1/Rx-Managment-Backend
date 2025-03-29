package com.surecostproject.takehome.exception;

import java.util.List;

public class BulkProcessingException extends RuntimeException {
    private final List<String> errors;

    public BulkProcessingException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
} 