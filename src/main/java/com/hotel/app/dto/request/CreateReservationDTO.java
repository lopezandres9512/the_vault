package com.hotel.app.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateReservationDTO(
        @NotBlank(message = "Reservation code is required")
        String code,

        @NotNull(message = "Reservation date is required")
        @FutureOrPresent(message = "Reservation date must be today or in the future")
        LocalDate reservationDate,

        @NotNull(message = "User ID is required")
        Long userId,

        @NotNull(message = "Room ID is required")
        Long roomId
) {}