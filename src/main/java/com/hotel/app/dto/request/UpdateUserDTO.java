package com.hotel.app.dto.request;

import com.hotel.app.model.User.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserDTO(
        String name,

        @Email(message = "Email must be valid")
        String email,

        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
        String username,

        @Size(min = 6, message = "Password must be at least 6 characters")
        String password,

        UserType userType
) {}