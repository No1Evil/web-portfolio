package dev.tsumakov.infrastructure.profile.experience.web.dto.response;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

public record UserExperiencePathResponse(
    UUID id,
    String title,
    String companyName,
    String location,
    Map<String, String> description,
    OffsetDateTime startDate,
    OffsetDateTime endDate,
    Boolean present
) {

}