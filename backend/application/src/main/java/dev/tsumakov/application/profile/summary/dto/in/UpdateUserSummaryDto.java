package dev.tsumakov.application.profile.summary.dto.in;

import java.time.OffsetDateTime;
import java.util.Map;

public record UpdateUserSummaryDto(
    String firstName,
    String lastName,
    String proficiency,
    Map<String, String> description,
    String heroImageUrl,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) {

}
