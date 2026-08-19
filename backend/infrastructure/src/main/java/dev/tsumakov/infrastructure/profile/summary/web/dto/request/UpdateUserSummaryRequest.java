package dev.tsumakov.infrastructure.profile.summary.web.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import java.util.Map;

public record UpdateUserSummaryRequest(
    @Nullable @NotBlank String firstName,
    @Nullable @NotBlank String lastName,
    @Nullable @NotBlank String proficiency,
    @Nullable Map<String, String> description,
    @Nullable String heroImageUrl
) {

}