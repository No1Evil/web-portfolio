package dev.tsumakov.application.core.skillcategory.dto.outer;

import java.time.OffsetDateTime;

public record SkillCategoryDto(
    Integer id,
    String name,
    String iconUrl,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) {

}
