package com.hotel.app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record UpdateHotelDTO(
        @NotBlank(message = "Hotel name is required")
        String name,

        @NotBlank(message = "Hotel code is required")
        String code,

        @Positive(message = "Room quantity must be positive")
        Integer roomQuantity,

        Long administratorId
) {}