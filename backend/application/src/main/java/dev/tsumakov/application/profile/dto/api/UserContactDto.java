package dev.tsumakov.application.profile.dto.api;

import java.util.Map;
import java.util.UUID;

public record UserContactDto(
    Integer id,
    UUID userId,
    Map<String, String> title,
    Map<String, String> subtitle,
    String redirectUrl,
    String iconUrl
) {

}
