package dev.tsumakov.infrastructure.core.skill.web.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateSkillRequest(
    @NotNull Integer categoryId,
    @NotBlank @Size(max = 50) String name,
    @Nullable String iconUrl
) {

}
