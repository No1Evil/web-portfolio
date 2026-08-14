package dev.tsumakov.infrastructure.portfolio.web.dto.skill;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SkillResponse(
    @NotNull Integer id,
    @NotNull Integer categoryId,
    @NotBlank String name
) {

}
