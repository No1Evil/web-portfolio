package dev.tsumakov.infrastructure.core.user.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserPasswordRequest(
    @NotBlank String oldRawPassword,
    @NotBlank @Size(min = 8) String rawPassword
) {

}
