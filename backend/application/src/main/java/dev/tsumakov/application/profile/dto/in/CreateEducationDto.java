package dev.tsumakov.application.profile.dto.in;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

public record CreateEducationDto(
    UUID userId,
    Map<String, String> institution,
    Map<String, String> degree,
    OffsetDateTime startDate,
    OffsetDateTime endDate
) {

}
