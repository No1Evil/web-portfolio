package dev.tsumakov.application.profile.dto.api;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

public record EducationDto(
    Integer id,
    UUID userId,
    Map<String, String> institution,
    Map<String, String> degree,
    OffsetDateTime startDate,
    OffsetDateTime endDate
) {

}
