package com.digitalskies.demo.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthRequest(
        @NotNull @Email(message = "Invalid email address") String email,
        @NotNull @Size(min = 4,message = "Password must be not be greater than 3 characters") String password) {
}
