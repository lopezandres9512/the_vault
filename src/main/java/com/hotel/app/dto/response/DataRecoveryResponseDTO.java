package com.hotel.app.dto.response;

import java.time.LocalDate;

public record DataRecoveryResponseDTO(
        Long id,
        LocalDate occupationDate,
        Long reservationId,
        String reservationCode,
        Long userId,
        String userName
) {}