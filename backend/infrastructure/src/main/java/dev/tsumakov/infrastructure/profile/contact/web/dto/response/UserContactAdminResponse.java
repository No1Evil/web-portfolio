package dev.tsumakov.infrastructure.profile.contact.web.dto.response;

import java.time.OffsetDateTime;

public record UserContactAdminResponse(
    Integer id,
    String title,
    String redirectUrl,
    String iconUrl,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) {

}