package com.hotel.app.dto.response;

import java.time.LocalDateTime;

public record HotelResponseDTO(
        Long id,
        String name,
        String code,
        Integer roomQuantity,
        Long administratorId,
        String administratorName,
        Integer totalRooms,
        LocalDateTime createdAt
) {}