package com.hotel.app.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponseDTO(
        Integer status,
        String error,
        String message,
        String path,
        LocalDateTime timestamp,
        List<String> validationErrors
) {
    public static ErrorResponseDTO of(Integer status, String error, String message, String path) {
        return new ErrorResponseDTO(status, error, message, path, LocalDateTime.now(), null);
    }

    public static ErrorResponseDTO withValidation(Integer status, String error, String message, String path, List<String> validationErrors) {
        return new ErrorResponseDTO(status, error, message, path, LocalDateTime.now(), validationErrors);
    }
}