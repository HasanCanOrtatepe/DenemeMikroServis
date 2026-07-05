package com.deneme.customer_service.core.exceptions;

public record FieldValidationError(
        String field,
        String message
) {
}
