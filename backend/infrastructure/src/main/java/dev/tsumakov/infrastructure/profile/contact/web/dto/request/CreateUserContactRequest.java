package dev.tsumakov.infrastructure.profile.contact.web.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserContactRequest(
    @NotBlank @Size(max = 50) String title,
    @Nullable String redirectUrl,
    @Nullable String iconUrl
) {

}