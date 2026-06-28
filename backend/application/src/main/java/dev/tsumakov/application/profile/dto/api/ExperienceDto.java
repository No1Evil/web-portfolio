package dev.tsumakov.application.profile.dto.api;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

public record ExperienceDto(
    Integer id,
    UUID userId,
    String company,
    String position,
    Map<String, String> description,
    OffsetDateTime startDate,
    OffsetDateTime endDate
) {

}
