package dev.tsumakov.infrastructure.core.user.web.dto.response;

import java.time.OffsetDateTime;

public record UserResponse(
    Integer id,
    String username,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) {

}
