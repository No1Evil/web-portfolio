package dev.tsumakov.application.profile.dto.in;

import java.util.Map;
import java.util.UUID;

public record UpdateUserProfileDto(
    UUID userId,
    Map<String, String> title,
    Map<String, String> description
) {

}
