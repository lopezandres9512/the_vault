package com.hotel.app.dto.response;

public record AuthResponseDTO(
        Long userId,
        String username,
        String email,
        String userType,
        String message,
        boolean isAuthenticated
) {}