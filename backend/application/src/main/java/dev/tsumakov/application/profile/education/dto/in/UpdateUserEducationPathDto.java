package dev.tsumakov.application.profile.education.dto.in;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

public record UpdateUserEducationPathDto(
    UUID userEducationPathId,
    String title,
    String companyName,
    String location,
    Map<String, String> description,
    OffsetDateTime startDate,
    OffsetDateTime endDate,
    Boolean present
) {

}
