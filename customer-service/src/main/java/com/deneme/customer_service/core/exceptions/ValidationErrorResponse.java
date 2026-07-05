package com.deneme.customer_service.core.exceptions;


import java.time.LocalDateTime;
import java.util.List;

public record ValidationErrorResponse(
        LocalDateTime timestamp,
        String status,
        String message,
        List<FieldValidationError> errors
) {
}