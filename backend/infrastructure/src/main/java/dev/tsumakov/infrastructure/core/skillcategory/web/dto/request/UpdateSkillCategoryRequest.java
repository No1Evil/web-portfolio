package dev.tsumakov.infrastructure.core.skillcategory.web.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateSkillCategoryRequest(
    @Nullable @NotBlank @Size(max = 50) String name,
    @Nullable String iconUrl
) {

}
