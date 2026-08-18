package dev.tsumakov.infrastructure.profile.summary.web.dto.response;

import java.time.OffsetDateTime;
import java.util.Map;

public record UserSummaryAdminResponse(
    Integer id,
    String firstName,
    String lastName,
    String proficiency,
    Map<String, String> description,
    String heroImageUrl,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) {

}