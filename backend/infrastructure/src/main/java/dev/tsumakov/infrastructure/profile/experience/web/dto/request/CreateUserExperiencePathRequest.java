package dev.tsumakov.infrastructure.profile.experience.web.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Map;

public record CreateUserExperiencePathRequest(
    @NotBlank String title,
    @NotBlank String companyName,
    @NotBlank String location,
    @NotNull Map<String, String> description,
    @Nullable OffsetDateTime startDate,
    @Nullable OffsetDateTime endDate,
    @NotNull Boolean present
) {

}