package dev.tsumakov.infrastructure.core.web.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
    @NotBlank(message = "First name cannot be blank")
    String firstName,
    @NotBlank(message = "Second name cannot be blank")
    String secondName,
    @Email(message = "Email cannot be empty")
    String email,
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password should be at least 8 chars long")
    String password,
    String avatarUrl
) {

}
