package dev.tsumakov.application.profile.dto.api;

import java.util.Map;
import java.util.UUID;

public record UserProfileDto(
    UUID userId,
    Map<String, String> title,
    Map<String, String> description
) {

}
