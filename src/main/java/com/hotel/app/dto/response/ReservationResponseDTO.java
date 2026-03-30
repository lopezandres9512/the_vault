package com.hotel.app.dto.response;

import java.time.LocalDate;

public record ReservationResponseDTO(
        Long id,
        String code,
        LocalDate reservationDate,
        Long userId,
        String userName,
        Long roomId,
        String roomCode,
        String hotelName
) {}