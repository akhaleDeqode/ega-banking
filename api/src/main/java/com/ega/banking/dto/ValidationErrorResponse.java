package com.ega.banking.dto;

import java.util.HashMap;
import java.util.Map;

public class ValidationErrorResponse {

    private Map<String, String> errors;

    public ValidationErrorResponse() {
        errors = new HashMap<>();
    }

    public void addError(String field, String message) {
        errors.put(field, message);
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
