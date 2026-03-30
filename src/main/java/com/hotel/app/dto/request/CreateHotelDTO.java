package com.hotel.app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateHotelDTO(
        @NotBlank String name,
        @NotBlank String code,
        @Positive Integer roomQuantity,
        Long administratorId
) {}