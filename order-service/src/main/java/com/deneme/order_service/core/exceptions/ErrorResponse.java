package com.deneme.order_service.core.exceptions;

import java.time.LocalDateTime;


public record ErrorResponse(
        LocalDateTime timestamp,
        String status,
        String message
) {
}