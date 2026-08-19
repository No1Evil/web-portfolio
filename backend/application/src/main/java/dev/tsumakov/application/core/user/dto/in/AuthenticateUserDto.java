package dev.tsumakov.application.core.user.dto.in;

public record AuthenticateUserDto(
    String username,
    String password
) {

}
