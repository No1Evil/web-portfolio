package dev.tsumakov.infrastructure.core.skill.web.dto.response;

import java.time.OffsetDateTime;

public record SkillAdminResponse(
    Integer id,
    Integer categoryId,
    String name,
    String iconUrl,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) {

}
