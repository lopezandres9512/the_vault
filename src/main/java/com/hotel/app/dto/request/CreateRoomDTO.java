package com.hotel.app.dto.request;

import com.hotel.app.model.Room.RoomState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotNull;

public record CreateRoomDTO(
        @NotBlank(message = "Room code is required")
        String code,

        @NotBlank(message = "Room size is required")
        String size,

        @Positive(message = "Person quantity must be positive")
        Integer personQuantity,

        @NotNull(message = "Room state is required")
        RoomState state,

        @NotNull(message = "Hotel ID is required")
        Long hotelId
) {}