package dev.tsumakov.application.profile.dto.in;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

public record CreateExperienceDto(
    UUID userId,
    String company,
    String position,
    Map<String, String> description,
    OffsetDateTime startDate,
    OffsetDateTime endDate
) {

}
