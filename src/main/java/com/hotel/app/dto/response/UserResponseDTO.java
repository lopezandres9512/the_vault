package com.hotel.app.dto.response;

import com.hotel.app.model.User.UserType;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String username,
        UserType userType,
        Integer totalHotels,
        Integer totalReservations
) {}