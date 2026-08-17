package dev.tsumakov.application.profile.experience.dto.in;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

public record UpdateUserExperiencePathDto(
    UUID userExperiencePathId,
    String title,
    String companyName,
    String location,
    Map<String, String> description,
    OffsetDateTime startDate,
    OffsetDateTime endDate,
    Boolean present
) {

}