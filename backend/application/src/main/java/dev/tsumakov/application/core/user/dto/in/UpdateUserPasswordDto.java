package dev.tsumakov.application.core.user.dto.in;

public record UpdateUserPasswordDto(
    Integer userId,
    String oldRawPassword,
    String rawPassword
) {

}
