package dev.tsumakov.application.core.dto.in;

public record CreateUserDto(
    String firstName,
    String secondName,
    String email,
    String password,
    String avatarUrl
) {

}
