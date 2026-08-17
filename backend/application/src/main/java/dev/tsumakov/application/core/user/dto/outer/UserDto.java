package dev.tsumakov.application.core.user.dto.outer;

import java.time.OffsetDateTime;

public record UserDto(
    Integer id,
    String username,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) {

}
