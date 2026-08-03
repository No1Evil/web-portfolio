package dev.tsumakov.infrastructure.core.web.dto.user;

public record UserResponse(
    String firstName,
    String lastName,
    String avatarUrl
) {

}
