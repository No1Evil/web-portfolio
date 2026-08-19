package dev.tsumakov.application.profile.experience.dto.outer;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

public record UserExperiencePathDto(
    UUID id,
    String title,
    String companyName,
    String location,
    Map<String, String> description,
    OffsetDateTime startDate,
    OffsetDateTime endDate,
    Boolean present,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) {

}
