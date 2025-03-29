package com.surecostproject.takehome.exception;

import java.util.UUID;

public class DrugNotFoundException extends RuntimeException {
    public DrugNotFoundException(UUID id) {
        super("Drug not found with id: " + id);
    }
} 