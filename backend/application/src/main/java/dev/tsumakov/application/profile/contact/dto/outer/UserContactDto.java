package dev.tsumakov.application.profile.contact.dto.outer;

import java.time.OffsetDateTime;

public record UserContactDto(
    Integer id,
    String title,
    String redirectUrl,
    String iconUrl,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) {

}
