package dev.tsumakov.application.profile.experience.dto.in;

import java.time.OffsetDateTime;
import java.util.Map;

public record CreateUserExperiencePathDto(
    String title,
    String companyName,
    String location,
    Map<String, String> description,
    OffsetDateTime startDate,
    OffsetDateTime endDate,
    Boolean present
) {

}
