package com.hotel.app.dto.request;

import com.hotel.app.model.Room.RoomState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record UpdateRoomDTO(
        @NotBlank(message = "Room code is required")
        String code,

        @NotBlank(message = "Room size is required")
        String size,

        @Positive(message = "Person quantity must be positive")
        Integer personQuantity,

        RoomState state,

        Long hotelId
) {}