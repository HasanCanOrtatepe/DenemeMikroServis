package com.deneme.product_service.core.exceptions;

public record FieldValidationError(
        String field,
        String message
) {
}
