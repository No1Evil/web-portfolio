package dev.tsumakov.application.profile.summary.dto.outer;

import java.time.OffsetDateTime;
import java.util.Map;

public record UserSummaryDto(
    Integer id,
    String firstName,
    String lastName,
    String proficiency,
    Map<String, String> description,
    String heroImageUrl,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) {

}
