package com.hotel.app.dto.response;

import com.hotel.app.model.Room.RoomState;

public record RoomResponseDTO(
        Long id,
        String code,
        String size,
        Integer personQuantity,
        RoomState state,
        Long hotelId,
        String hotelName
) {}