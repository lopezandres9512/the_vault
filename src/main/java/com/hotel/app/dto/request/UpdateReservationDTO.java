package com.hotel.app.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record UpdateReservationDTO(
        String code,

        @FutureOrPresent(message = "Reservation date must be today or in the future")
        LocalDate reservationDate,

        Long userId,

        Long roomId
) {}