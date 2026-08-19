package dev.tsumakov.application.profile.education.dto.in;

import java.time.OffsetDateTime;
import java.util.Map;

public record CreateUserEducationPathDto(
    String title,
    String location,
    Map<String, String> description,
    OffsetDateTime startDate,
    OffsetDateTime endDate,
    Boolean present
) {

}
