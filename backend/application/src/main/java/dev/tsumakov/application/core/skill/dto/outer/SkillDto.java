package dev.tsumakov.application.core.skill.dto.outer;

import java.time.OffsetDateTime;

public record SkillDto(
    Integer id,
    Integer categoryId,
    String name,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) {

}
