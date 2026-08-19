package dev.tsumakov.infrastructure.core.skillcategory.web.dto.response;

import java.time.OffsetDateTime;

public record SkillCategoryAdminResponse(
    Integer id,
    String name,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) {

}
