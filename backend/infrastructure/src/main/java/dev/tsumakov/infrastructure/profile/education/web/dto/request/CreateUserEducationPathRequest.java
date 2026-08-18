package dev.tsumakov.infrastructure.profile.education.web.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Map;

public record CreateUserEducationPathRequest(
    @NotBlank String title,
    @NotBlank String location,
    @NotNull Map<String, String> description,
    @Nullable OffsetDateTime startDate,
    @Nullable OffsetDateTime endDate,
    @NotNull Boolean present
) {

}