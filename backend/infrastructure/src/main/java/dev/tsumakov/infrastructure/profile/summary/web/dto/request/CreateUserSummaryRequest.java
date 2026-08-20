package dev.tsumakov.infrastructure.profile.summary.web.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Map;

public record CreateUserSummaryRequest(
    @NotBlank String firstName,
    @NotBlank String lastName,
    @NotBlank String proficiency,
    @NotNull Map<String, String> description,
    @Nullable String heroImageUrl
) {

}