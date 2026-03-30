package com.hotel.app.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateDataRecoveryDTO(
        @NotNull(message = "Occupation date is required")
        LocalDate occupationDate,

        @NotNull(message = "Reservation ID is required")
        Long reservationId,

        @NotNull(message = "User ID is required")
        Long userId
) {}