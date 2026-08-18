package dev.tsumakov.infrastructure.core.user.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthenticateUserRequest(
    @NotNull @NotBlank String username,
    @NotNull @NotBlank String password
) {

}
