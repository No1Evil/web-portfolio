package dev.tsumakov.application.profile.education.dto.outer;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

public record UserEducationPathDto(
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
