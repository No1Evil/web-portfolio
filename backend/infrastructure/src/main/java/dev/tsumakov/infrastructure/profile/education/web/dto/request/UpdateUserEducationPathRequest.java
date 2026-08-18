package dev.tsumakov.infrastructure.profile.education.web.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import java.time.OffsetDateTime;
import java.util.Map;

public record UpdateUserEducationPathRequest(
    @Nullable @NotBlank String title,
    @Nullable @NotBlank String location,
    @Nullable Map<String, String> description,
    @Nullable OffsetDateTime startDate,
    @Nullable OffsetDateTime endDate,
    @Nullable Boolean present
) {

}