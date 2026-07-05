package com.deneme.order_service.core.exceptions;

public record FieldValidationError(
        String field,
        String message
) {
}
