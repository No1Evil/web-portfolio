package dev.tsumakov.infrastructure.profile.education.web.dto.response;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

public record UserEducationPathAdminResponse(
    UUID id,
    String title,
    String location,
    Map<String, String> description,
    OffsetDateTime startDate,
    OffsetDateTime endDate,
    Boolean present,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) {

}