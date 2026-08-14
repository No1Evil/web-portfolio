package dev.tsumakov.infrastructure.core.web.dto.user;

public record UserCreatedResponse(
    String firstName,
    String lastName,
    String email,
    String avatarUrl
) {

}
