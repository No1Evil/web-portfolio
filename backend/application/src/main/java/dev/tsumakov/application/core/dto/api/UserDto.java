package dev.tsumakov.application.core.dto.api;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record UserDto(
    UUID id,
    String firstName,
    String lastName,
    String email,
    String avatarUrl,
    List<RoleDto> roles,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) {

}
